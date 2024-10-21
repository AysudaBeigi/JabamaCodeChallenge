package com.jabama.challenge.data.mapper

import com.jabama.challenge.data.model.RepositoryDto
import com.jabama.challenge.data.model.RequestAccessTokenDto
import com.jabama.challenge.data.model.ResponseAccessTokenDto
import com.jabama.challenge.domain.model.Repository
import com.jabama.challenge.domain.model.RequestAccessToken
import com.jabama.challenge.domain.model.ResponseAccessToken

internal fun RequestAccessToken.mapToRequestAccessTokenDto() = RequestAccessTokenDto(
    clientId = clientId,
    clientSecret = clientSecret,
    code = code,
    redirectUri = redirectUri,
    state = state,
)

internal fun ResponseAccessTokenDto.mapToResponseAccessToken() = ResponseAccessToken(
    accessToken = accessToken,
    tokenType = tokenType,
)

internal fun RepositoryDto.mapToRepository() = Repository(
    name = name,
    language = language,
    isFavorite = isFavorite,
    stargazers_count = stargazers_count,
    updated_at = updated_at,
    visibility = visibility,
)