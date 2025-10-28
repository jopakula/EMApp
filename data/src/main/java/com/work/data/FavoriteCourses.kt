package com.work.data

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteCourses(
    val favoriteIds: List<Int> = emptyList()
)