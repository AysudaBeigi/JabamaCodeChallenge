package com.jabama.challenge.di

import com.jabama.challenge.base.coroutineDispatcherProvider
import com.jabama.challenge.data.repository.GithubRepositoryImpl
import com.jabama.challenge.data.repository.TokenRepositoryImpl
import com.jabama.challenge.domain.IGithubRepository
import com.jabama.challenge.domain.ITokenRepository
import com.jabama.challenge.domain.usecase.GetAccessTokenUseCase
import com.jabama.challenge.domain.usecase.GetRepositoryListUseCase
import com.jabama.challenge.domain.usecase.ReadTokenUseCase
import com.jabama.challenge.domain.usecase.SaveTokenUseCase
import com.jabama.challenge.presentation.viewmodel.AuthorizeViewModel
import com.jabama.challenge.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val githubModule = module {

    single<IGithubRepository> { GithubRepositoryImpl(get()) }
    single<ITokenRepository> { TokenRepositoryImpl(get(), coroutineDispatcherProvider()) }

    single { TokenRepositoryImpl(get(), coroutineDispatcherProvider()) }

    viewModel {
        RepositoryViewModel(get(), get(), coroutineDispatcherProvider())
    }
    viewModel {
        AuthorizeViewModel(get(), get(), coroutineDispatcherProvider())
    }

    factory { ReadTokenUseCase(get()) }

    factory { SaveTokenUseCase(get()) }

    factory { GetAccessTokenUseCase(get()) }

    factory { GetRepositoryListUseCase(get()) }

}