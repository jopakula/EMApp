package com.work.emapp.ui.account

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.domain.models.Course
import com.work.domain.useCases.GetCoursesUseCase
import com.work.domain.useCases.GetFavoriteIdsFlowUseCase
import kotlinx.coroutines.launch

class AccountViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getFavoriteIdsFlowUseCase: GetFavoriteIdsFlowUseCase
) : ViewModel() {

    private val _courses = mutableStateOf<List<Course>>(emptyList())
    val courses: State<List<Course>> = _courses

    private val _favoriteIds = mutableStateOf<List<Int>>(emptyList())
    val favoriteIds: State<List<Int>> = _favoriteIds

    val sortedCourses: State<List<Course>> = derivedStateOf {
        _courses.value.sortedWith(
            compareByDescending<Course> { _favoriteIds.value.contains(it.id) }
                .thenBy { it.title }
        )
    }

    init {
        viewModelScope.launch {
            _courses.value = getCoursesUseCase()
            getFavoriteIdsFlowUseCase().collect { ids ->
                _favoriteIds.value = ids
            }
        }
    }
}