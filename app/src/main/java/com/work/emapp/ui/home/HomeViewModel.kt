package com.work.emapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.data.Course
import com.work.data.FavoritesRepository
import com.work.data.MockData
import kotlinx.coroutines.launch

class HomeViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {
    private val _courses = mutableStateOf<List<Course>>(emptyList())
    val courses: State<List<Course>> = _courses

    private val _selectedCourse = mutableStateOf<Course?>(null)
    val selectedCourse: State<Course?> = _selectedCourse

    private val _favoriteIds = mutableStateOf<List<Int>>(emptyList())
    val favoriteIds: State<List<Int>> = _favoriteIds

    init {
        viewModelScope.launch {
            _courses.value = MockData.getCourses()
            favoritesRepository.getFavoriteIdsFlow().collect { favoriteCourses ->
                _favoriteIds.value = favoriteCourses.favoriteIds
            }
        }
    }

    fun chooseCourse(course: Course) {
        _selectedCourse.value = course
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

    suspend fun isCourseFavorite(courseId: Int): Boolean {
        return favoritesRepository.isFavorite(courseId)
    }
}