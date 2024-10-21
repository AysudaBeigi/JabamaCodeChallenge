package com.jabama.challenge.domain.usecase

import android.net.Uri
import com.jabama.challenge.domain.IGithubRepository
import com.jabama.challenge.domain.model.RequestAccessToken

private const val CLIENT_ID = "Ov23lieNfZen0Ac2lX66"
private const val CLIENT_SECRET = "b49541b80e0b3db47a8e5a785749f1fdf32baa27"
private const val REDIRECT_URI = "https://github.com/AysudaBeigi/JabamaCodeChallenge"
private const val url =
    "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=0"

private const val CODE = "code"

class GetAccessTokenUseCase(private val repository: IGithubRepository) {
    suspend fun execute() = Uri.parse(url).getQueryParameter(CODE)?.let {
        repository.accessToken(
            requestAccessToken = RequestAccessToken(
                code = it,
                state = "0",
                clientSecret = CLIENT_SECRET,
                clientId = CLIENT_ID,
                redirectUri = REDIRECT_URI,
            )
        )
    }
}