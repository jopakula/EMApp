package com.work.emapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.domain.models.Course
import com.work.domain.useCases.GetCoursesUseCase
import com.work.domain.useCases.GetFavoriteIdsFlowUseCase
import com.work.domain.useCases.ToggleFavoriteUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getFavoriteIdsFlowUseCase: GetFavoriteIdsFlowUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _rawCourses = mutableStateOf<List<Course>>(emptyList())
    private val _sortDescending = mutableStateOf(true)
    val sortState: State<Boolean> = _sortDescending

    val courses: State<List<Course>> = derivedStateOf {
        val sorted = if (_sortDescending.value) {
            _rawCourses.value.sortedByDescending { it.publishDate }
        } else {
            _rawCourses.value.sortedBy { it.publishDate }
        }
        sorted
    }

    private val _selectedCourse = mutableStateOf<Course?>(null)
    val selectedCourse: State<Course?> = _selectedCourse

    private val _favoriteIds = mutableStateOf<List<Int>>(emptyList())
    val favoriteIds: State<List<Int>> = _favoriteIds

    init {
        viewModelScope.launch {
            _rawCourses.value = getCoursesUseCase()
            getFavoriteIdsFlowUseCase().collect { ids ->
                _favoriteIds.value = ids
            }
        }
    }

    fun chooseCourse(course: Course) {
        _selectedCourse.value = course
    }

    fun toggleFavorite(courseId: Int) {
        viewModelScope.launch {
            toggleFavoriteUseCase(courseId)
        }
    }

    fun toggleSortOrder() {
        _sortDescending.value = !_sortDescending.value
    }
}