package com.work.emapp.di

import com.work.domain.useCases.GetCoursesUseCase
import com.work.domain.useCases.GetFavoriteIdsFlowUseCase
import com.work.domain.useCases.SortCoursesUseCase
import com.work.domain.useCases.ToggleFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetCoursesUseCase( coursesRepository = get()) }

    factory { GetFavoriteIdsFlowUseCase(favoritesRepository = get()) }

    factory { ToggleFavoriteUseCase(favoritesRepository = get()) }

    factory { SortCoursesUseCase() }

}