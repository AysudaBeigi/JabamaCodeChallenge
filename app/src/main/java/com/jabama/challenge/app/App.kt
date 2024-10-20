package com.jabama.challenge.app

import android.app.Application
import com.jabama.challenge.di.appModule
import com.jabama.challenge.network.di.accessTokenModule
import com.jabama.challenge.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, accessTokenModule))
        }
    }
}