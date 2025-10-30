package com.work.domain.useCases

import com.work.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteIdsFlowUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<Int>> {
        return favoritesRepository.getFavoriteIdsFlow()
            .map { it.favoriteIds }
    }
}