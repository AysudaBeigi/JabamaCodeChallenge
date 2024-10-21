package com.jabama.challenge.domain

import com.jabama.challenge.data.model.RequestAccessTokenDto
import com.jabama.challenge.domain.model.ResponseAccessToken

interface IAccessTokenDataSource {
   suspend fun accessToken(requestAccessTokenDto: RequestAccessTokenDto): ResponseAccessToken
}