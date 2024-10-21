package com.jabama.challenge.data.repository

import com.jabama.challenge.data.api.AccessTokenService
import com.jabama.challenge.data.model.RequestAccessToken
import com.jabama.challenge.domain.AccessTokenDataSource

const val TOKEN = "TOKEN"
class AccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    AccessTokenDataSource {
    override fun accessToken(requestAccessToken: RequestAccessToken) = accessTokenService.accessToken(requestAccessToken)
}