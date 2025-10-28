package com.work.emapp.di

import com.work.emapp.ui.account.AccountViewModel
import com.work.emapp.ui.favorites.FavoritesViewModel
import com.work.emapp.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        HomeViewModel(
            getCoursesUseCase = get(),
            getFavoriteIdsFlowUseCase = get(),
            toggleFavoriteUseCase = get(),

        )
    }

    viewModel {
        FavoritesViewModel(
            getCoursesUseCase = get(),
            getFavoriteIdsFlowUseCase = get(),
            toggleFavoriteUseCase = get(),
        )
    }

    viewModel {
        AccountViewModel(
            getCoursesUseCase = get(),
            getFavoriteIdsFlowUseCase = get(),
        )
    }

}