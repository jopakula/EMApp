package com.work.emapp.di

import com.work.data.FavoritesRepository
import com.work.data.FavoritesRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single<FavoritesRepository> { FavoritesRepositoryImpl(application = androidApplication()) }

}