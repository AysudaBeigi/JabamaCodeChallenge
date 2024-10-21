package com.jabama.challenge.di

import com.jabama.challenge.data.repository.AccessTokenDataSourceImpl
import com.jabama.challenge.data.repository.TokenRepositoryImpl
import com.jabama.challenge.domain.AccessTokenDataSource
import org.koin.dsl.module

val accessTokenModule = module {
    factory { AccessTokenDataSourceImpl(get()) as AccessTokenDataSource }
    single { TokenRepositoryImpl(get()) }
}