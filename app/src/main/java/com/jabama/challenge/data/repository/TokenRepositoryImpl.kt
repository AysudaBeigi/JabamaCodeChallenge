package com.jabama.challenge.data.repository

import android.content.SharedPreferences
import com.jabama.challenge.domain.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TokenRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    TokenRepository {
    override fun saveToken(token: String): Deferred<Unit> =
        CoroutineScope(Dispatchers.IO).async { sharedPreferences.edit().apply { putString(TOKEN, token) }.apply() }


    override fun readToken(): Deferred<String> =
        CoroutineScope(Dispatchers.IO).async { sharedPreferences.getString(TOKEN, "") ?: "" }
}