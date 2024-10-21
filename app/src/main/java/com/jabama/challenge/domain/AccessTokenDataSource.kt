package com.jabama.challenge.domain

import kotlinx.coroutines.Deferred

interface AccessTokenDataSource {
    fun accessToken(requestAccessToken: com.jabama.challenge.data.model.RequestAccessToken): Deferred<com.jabama.challenge.data.model.ResponseAccessToken>
}