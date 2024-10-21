package com.jabama.challenge.domain.usecase

import com.jabama.challenge.domain.IAccessTokenDataSource
import com.jabama.challenge.domain.model.RequestAccessToken

class GetAccessTokenUseCase(private val repository: IAccessTokenDataSource) {
    suspend fun accessToken(requestAccessToken: RequestAccessToken) = repository.accessToken(
        requestAccessToken = requestAccessToken
    )
}