package com.jabama.challenge.domain.usecase

import com.jabama.challenge.domain.IGithubRepository

class GetRepositoryListUseCase(private val repository: IGithubRepository) {
    suspend fun execute(token: String) = repository.getRepositoryList(token = token)
}