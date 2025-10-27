package com.work.emapp

import android.app.Application
import com.work.emapp.di.appModule
import com.work.emapp.di.dataModule
import com.work.emapp.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.ERROR)
            modules(listOf(appModule, dataModule, domainModule))
        }

    }
}