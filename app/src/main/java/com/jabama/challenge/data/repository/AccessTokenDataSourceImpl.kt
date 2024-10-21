package com.jabama.challenge.data.repository

import com.jabama.challenge.data.api.AccessTokenService
import com.jabama.challenge.data.mapper.mapToRequestAccessTokenDto
import com.jabama.challenge.data.mapper.mapToResponseAccessToken
import com.jabama.challenge.domain.IAccessTokenDataSource
import com.jabama.challenge.domain.model.RequestAccessToken

class IAccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    IAccessTokenDataSource {
    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenService.accessToken(requestAccessToken.mapToRequestAccessTokenDto())
            .mapToResponseAccessToken()
}