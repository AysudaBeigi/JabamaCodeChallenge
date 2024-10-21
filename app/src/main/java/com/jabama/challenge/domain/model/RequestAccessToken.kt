package com.jabama.challenge.domain.model


data class RequestAccessToken(
    var clientId: String,
    var clientSecret: String,
    var code: String,
    var redirectUri: String,
    var state: String
)