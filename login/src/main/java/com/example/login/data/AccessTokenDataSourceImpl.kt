package com.example.login.data

import com.example.login.domain.AccessTokenDataSource

class AccessTokenDataSourceImpl(private val accessTokenService: com.example.login.data.api.AccessTokenService) :
    AccessTokenDataSource {
    override fun accessToken(requestAccessToken: com.example.login.data.model.RequestAccessToken) = accessTokenService.accessToken(requestAccessToken)
}