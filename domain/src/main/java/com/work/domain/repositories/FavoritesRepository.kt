package com.work.domain.repositories


import com.work.domain.models.FavoriteCourses
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavoriteIdsFlow(): Flow<FavoriteCourses>
    suspend fun getFavoriteIds(): List<Int>
    suspend fun addFavorite(courseId: Int)
    suspend fun removeFavorite(courseId: Int)
    suspend fun isFavorite(courseId: Int): Boolean
}