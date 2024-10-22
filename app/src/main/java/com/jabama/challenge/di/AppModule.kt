package com.jabama.challenge.di

import androidx.preference.PreferenceManager
import com.jabama.challenge.data.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}
