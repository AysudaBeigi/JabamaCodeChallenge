package com.jabama.challenge.data.repository

import com.jabama.challenge.data.api.ApiService
import com.jabama.challenge.data.mapper.mapToRepository
import com.jabama.challenge.data.mapper.mapToRequestAccessTokenDto
import com.jabama.challenge.data.mapper.mapToResponseAccessToken
import com.jabama.challenge.domain.IGithubRepository
import com.jabama.challenge.domain.model.Repository
import com.jabama.challenge.domain.model.RequestAccessToken

class GithubRepositoryImpl(private val apiService: ApiService) :
    IGithubRepository {
    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        apiService.accessToken(requestAccessToken.mapToRequestAccessTokenDto())
            .mapToResponseAccessToken()

    override suspend fun getRepositoryList(token: String): List<Repository> {
        return apiService.getUserRepositories(token = token).map { it.mapToRepository() }
    }
}