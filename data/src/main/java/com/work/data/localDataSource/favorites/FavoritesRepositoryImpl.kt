package com.work.data.localDataSource.favorites

import android.app.Application
import com.work.domain.models.FavoriteCourses
import com.work.domain.repositories.FavoritesRepository
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

class FavoritesRepositoryImpl(
    application: Application
) : FavoritesRepository {

    private val mainDir = application.filesDir.path

    companion object {
        private const val PREFS_FILE_NAME = "favorite_courses.json"
    }

    private val store: KStore<FavoriteCourses> = storeOf(
        file = "$mainDir/$PREFS_FILE_NAME".toPath()
    )

    override fun getFavoriteIdsFlow(): Flow<FavoriteCourses> =
        store.updates.map { it ?: FavoriteCourses() }

    override suspend fun getFavoriteIds(): List<Int> =
        store.get()?.favoriteIds ?: emptyList()

    override suspend fun addFavorite(courseId: Int) {
        val currentFavorites = store.get()?.favoriteIds ?: emptyList()
        if (courseId !in currentFavorites) {
            store.set(FavoriteCourses(currentFavorites + courseId))
        }
    }

    override suspend fun removeFavorite(courseId: Int) {
        val currentFavorites = store.get()?.favoriteIds ?: emptyList()
        if (courseId in currentFavorites) {
            store.set(FavoriteCourses(currentFavorites - courseId))
        }
    }

    override suspend fun isFavorite(courseId: Int): Boolean =
        store.get()?.favoriteIds?.contains(courseId) ?: false
}