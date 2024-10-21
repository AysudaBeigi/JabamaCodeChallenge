package com.jabama.challenge.di

import com.jabama.challenge.base.coroutineDispatcherProvider
import com.jabama.challenge.data.repository.GithubRepositoryImpl
import com.jabama.challenge.data.repository.TokenRepositoryImpl
import com.jabama.challenge.domain.IGithubRepository
import org.koin.dsl.module

val accessTokenModule = module {
    factory<IGithubRepository> { GithubRepositoryImpl(get()) }
    single { TokenRepositoryImpl(get(),coroutineDispatcherProvider()) }
}