package com.jabama.challenge.di

import androidx.preference.PreferenceManager
import com.jabama.challenge.data.api.ApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"

val appModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(ApiService::class.java) }
   //single(named(APPLICATION_CONTEXT)) { applicationContext }
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
}
