package com.jabama.challenge.data.repository

import com.jabama.challenge.data.api.AccessTokenService
import com.jabama.challenge.data.model.RequestAccessTokenDto
import com.jabama.challenge.domain.IAccessTokenDataSource

const val TOKEN = "TOKEN"
class IAccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    IAccessTokenDataSource {
    override fun accessToken(requestAccessTokenDto: RequestAccessTokenDto) = accessTokenService.accessToken(requestAccessTokenDto)
}