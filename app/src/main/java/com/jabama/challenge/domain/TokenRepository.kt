package com.jabama.challenge.domain

import kotlinx.coroutines.Deferred

interface TokenRepository {
    fun saveToken(token: String) : Deferred<Unit>
    fun readToken(): Deferred<String>
}