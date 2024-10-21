package com.jabama.challenge.data.repository

import com.jabama.challenge.data.api.AccessTokenService
import com.jabama.challenge.data.mapper.mapToResponseAccessToken
import com.jabama.challenge.data.model.RequestAccessTokenDto
import com.jabama.challenge.domain.IAccessTokenDataSource

class IAccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    IAccessTokenDataSource {
    override suspend fun accessToken(requestAccessTokenDto: RequestAccessTokenDto) =
        accessTokenService.accessToken(requestAccessTokenDto).mapToResponseAccessToken()
}