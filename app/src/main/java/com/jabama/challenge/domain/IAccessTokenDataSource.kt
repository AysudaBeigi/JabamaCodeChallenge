package com.jabama.challenge.domain

import com.jabama.challenge.data.model.RequestAccessTokenDto
import com.jabama.challenge.data.model.ResponseAccessTokenDto
import kotlinx.coroutines.Deferred

interface IAccessTokenDataSource {
    fun accessToken(requestAccessTokenDto: RequestAccessTokenDto): Deferred<ResponseAccessTokenDto>
}