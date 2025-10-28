package com.work.domain.useCases

import com.work.domain.repositories.FavoritesRepository

class ToggleFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(courseId: Int) {
        if (favoritesRepository.isFavorite(courseId)) {
            favoritesRepository.removeFavorite(courseId)
        } else {
            favoritesRepository.addFavorite(courseId)
        }
    }
}