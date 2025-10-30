package com.work.emapp.ui.favorites

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

class FavoritesViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getFavoriteIdsFlowUseCase: GetFavoriteIdsFlowUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _courses = mutableStateOf<List<Course>>(emptyList())

    private val _favoriteIds = mutableStateOf<List<Int>>(emptyList())

    val favoriteCourses: State<List<Course>> = derivedStateOf {
        _courses.value.filter { it.id in _favoriteIds.value }
    }

    init {
        viewModelScope.launch {
            _courses.value = getCoursesUseCase()
            getFavoriteIdsFlowUseCase().collect { ids ->
                _favoriteIds.value = ids
            }
        }
    }

    fun toggleFavorite(courseId: Int) {
        viewModelScope.launch {
            toggleFavoriteUseCase(courseId)
        }
    }
}