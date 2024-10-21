package com.jabama.challenge.domain.usecase

import com.jabama.challenge.domain.IGithubRepository
import com.jabama.challenge.domain.model.RequestAccessToken

class GetAccessTokenUseCase(private val repository: IGithubRepository) {
    suspend fun execute(requestAccessToken: RequestAccessToken) = repository.accessToken(
        requestAccessToken = requestAccessToken
    )
}