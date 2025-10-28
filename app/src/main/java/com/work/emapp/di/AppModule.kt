package com.work.emapp.di

import com.work.emapp.ui.favorites.FavoritesViewModel
import com.work.emapp.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        HomeViewModel(
            favoritesRepository = get(),
            coursesRepository = get(),
        )
    }

    viewModel {
        FavoritesViewModel(
            favoritesRepository = get(),
            coursesRepository = get(),
        )
    }

}