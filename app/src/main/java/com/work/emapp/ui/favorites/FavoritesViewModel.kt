package com.work.emapp.ui.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.data.localDataSource.courses.CoursesRepository
import com.work.data.localDataSource.favorites.FavoritesRepository
import com.work.data.localDataSource.models.Course
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val coursesRepository: CoursesRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _courses = mutableStateOf<List<Course>>(emptyList())
    val courses: State<List<Course>> = _courses

    private val _favoriteIds = mutableStateOf<List<Int>>(emptyList())
    val favoriteIds: State<List<Int>> = _favoriteIds

    val favoriteCourses: State<List<Course>> = derivedStateOf {
        _courses.value.filter { it.id in _favoriteIds.value }
    }

    init {
        viewModelScope.launch {
            _courses.value = coursesRepository.getCourses()

            favoritesRepository.getFavoriteIdsFlow().collect { data ->
                _favoriteIds.value = data.favoriteIds
            }
        }
    }

    fun toggleFavorite(courseId: Int) {
        viewModelScope.launch {
            if (favoritesRepository.isFavorite(courseId)) {
                favoritesRepository.removeFavorite(courseId)
            } else {
                favoritesRepository.addFavorite(courseId)
            }
        }
    }
}