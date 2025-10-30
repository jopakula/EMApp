package com.work.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteCourses(
    val favoriteIds: List<Int> = emptyList()
)