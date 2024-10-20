package com.example.login.domain

import com.example.login.data.model.RequestAccessToken
import com.example.login.data.model.ResponseAccessToken
import kotlinx.coroutines.Deferred

interface AccessTokenDataSource {
    fun accessToken(requestAccessToken: RequestAccessToken): Deferred<ResponseAccessToken>
}