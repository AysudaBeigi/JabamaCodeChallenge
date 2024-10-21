package com.jabama.challenge.domain

import kotlinx.coroutines.Deferred

interface ITokenRepository {
    fun saveToken(token: String) : Deferred<Unit>
    fun readToken(): Deferred<String>
}