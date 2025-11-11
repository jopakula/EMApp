package com.work.emapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.domain.models.Course
import com.work.domain.useCases.GetCoursesUseCase
import com.work.domain.useCases.GetFavoriteIdsFlowUseCase
import com.work.domain.useCases.SortCoursesUseCase
import com.work.domain.useCases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getFavoriteIdsFlowUseCase: GetFavoriteIdsFlowUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val sortCoursesUseCase: SortCoursesUseCase,
) : ViewModel() {

    private val _rawCourses = MutableStateFlow<List<Course>>(emptyList())

    private val _sortDescending = MutableStateFlow(true)
    val sortDescending: StateFlow<Boolean> = _sortDescending

    val courses: StateFlow<List<Course>> = combine(
        _rawCourses,
        _sortDescending
    ) { rawCourses, descending ->
        sortCoursesUseCase(rawCourses, ascending = !descending)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

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