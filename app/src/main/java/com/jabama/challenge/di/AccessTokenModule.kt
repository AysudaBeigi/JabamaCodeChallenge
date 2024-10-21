package com.jabama.challenge.di

import com.jabama.challenge.base.coroutineDispatcherProvider
import com.jabama.challenge.data.repository.IAccessTokenDataSourceImpl
import com.jabama.challenge.data.repository.TokenRepositoryImpl
import com.jabama.challenge.domain.IAccessTokenDataSource
import org.koin.dsl.module

val accessTokenModule = module {
    factory<IAccessTokenDataSource> { IAccessTokenDataSourceImpl(get()) }
    single { TokenRepositoryImpl(get(),coroutineDispatcherProvider()) }
}