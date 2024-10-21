package com.jabama.challenge.domain

interface ITokenRepository {
    suspend fun saveToken(token: String)
    suspend fun readToken(): String
}