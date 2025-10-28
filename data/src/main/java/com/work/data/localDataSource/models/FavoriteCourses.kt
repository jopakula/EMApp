package com.work.data.localDataSource.models

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteCourses(
    val favoriteIds: List<Int> = emptyList()
)