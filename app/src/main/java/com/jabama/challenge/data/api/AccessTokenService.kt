package com.jabama.challenge.data.api

import com.jabama.challenge.data.model.RequestAccessToken
import com.jabama.challenge.data.model.ResponseAccessToken
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccessTokenService {
    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    fun accessToken(@Body requestAccessToken: RequestAccessToken): Deferred<ResponseAccessToken>
}