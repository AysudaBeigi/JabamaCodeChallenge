package com.example.login.di

import com.example.login.data.AccessTokenDataSourceImpl
import com.example.login.data.repository.TokenRepositoryImpl
import com.example.login.domain.AccessTokenDataSource
import org.koin.dsl.module

val accessTokenModule = module {
    factory { AccessTokenDataSourceImpl(get()) as AccessTokenDataSource }
    single { TokenRepositoryImpl(get()) }
}