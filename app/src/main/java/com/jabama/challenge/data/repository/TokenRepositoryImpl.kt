package com.jabama.challenge.data.repository

import android.content.SharedPreferences
import com.jabama.challenge.base.CoroutineDispatcherProvider
import com.jabama.challenge.domain.ITokenRepository
import kotlinx.coroutines.withContext

private const val TOKEN = "TOKEN"

class TokenRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ITokenRepository {

    override suspend fun saveToken(token: String): Unit =
        withContext(coroutineDispatcherProvider.ioDispatcher()) {
            sharedPreferences.edit().apply { putString(TOKEN, token) }.apply()
        }

    override suspend fun readToken(): String =
        withContext(coroutineDispatcherProvider.ioDispatcher()) {
            sharedPreferences.getString(
                TOKEN,
                ""
            ) ?: ""
        }
}