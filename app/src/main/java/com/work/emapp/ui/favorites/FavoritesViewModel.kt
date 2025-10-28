package com.work.emapp.ui.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.data.Course
import com.work.data.FavoritesRepository
import com.work.data.MockData
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _courses = mutableStateOf<List<Course>>(emptyList())
    val courses: State<List<Course>> = _courses

    private val _favoriteIds = mutableStateOf<List<Int>>(emptyList())
    val favoriteIds: State<List<Int>> = _favoriteIds

    val favoriteCourses: State<List<Course>>
        get() = derivedStateOf {
            _courses.value.filter { it.id in _favoriteIds.value }
        }

    init {
        viewModelScope.launch {
            _courses.value = MockData.getCourses()

            favoritesRepository.getFavoriteIdsFlow().collect { favoriteData ->
                _favoriteIds.value = favoriteData.favoriteIds
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