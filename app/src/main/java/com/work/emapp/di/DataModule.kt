package com.work.emapp.di

import com.work.data.localDataSource.courses.CoursesRepository
import com.work.data.localDataSource.courses.CoursesRepositoryImpl
import com.work.data.localDataSource.favorites.FavoritesRepository
import com.work.data.localDataSource.favorites.FavoritesRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single<CoursesRepository> { CoursesRepositoryImpl() }

    single<FavoritesRepository> { FavoritesRepositoryImpl(application = androidApplication()) }

}