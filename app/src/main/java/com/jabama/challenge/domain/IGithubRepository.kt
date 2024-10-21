package com.jabama.challenge.domain

import com.jabama.challenge.domain.model.Repository
import com.jabama.challenge.domain.model.RequestAccessToken
import com.jabama.challenge.domain.model.ResponseAccessToken

interface IGithubRepository {
    suspend fun accessToken(requestAccessToken: RequestAccessToken): ResponseAccessToken
    suspend fun getRepositoryList(token: String): List<Repository>
}