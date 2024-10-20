package com.jabama.challenge

import android.app.Application
import com.example.login.di.accessTokenModule
import com.jabama.challenge.di.appModule
import com.jabama.challenge.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(appModule, networkModule,accessTokenModule))
        }
    }
}