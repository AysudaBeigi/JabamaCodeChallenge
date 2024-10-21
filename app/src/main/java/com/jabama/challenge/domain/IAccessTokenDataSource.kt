package com.jabama.challenge.domain

import com.jabama.challenge.domain.model.RequestAccessToken
import com.jabama.challenge.domain.model.ResponseAccessToken

interface IAccessTokenDataSource {
   suspend fun accessToken(requestAccessToken: RequestAccessToken): ResponseAccessToken
}