package com.jabama.challenge.di

import androidx.preference.PreferenceManager
import com.jabama.challenge.repository.token.TokenRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"

val appModule = module {
    single { TokenRepositoryImpl(get()) }
   //single(named(APPLICATION_CONTEXT)) { applicationContext }
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
}
