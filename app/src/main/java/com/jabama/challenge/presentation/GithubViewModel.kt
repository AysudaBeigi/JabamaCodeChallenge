package com.jabama.challenge.presentation

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.domain.model.RequestAccessToken
import com.jabama.challenge.domain.usecase.GetAccessTokenUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

const val CLIENT_ID = "CLIENT_ID"
const val CLIENT_SECRET = "CLIENT_SECRET"
const val REDIRECT_URI = "REDIRECT_URI"
class GithubViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    override val coroutineContext: CoroutineContext
) : ViewModel(), CoroutineScope {


    init {

    }

    fun accessToken() {
        viewModelScope.launch {
            getAccessTokenUseCase.accessToken()
        }
    }

    fun authorize() {
        val url =
            "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=0"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun login() {
        val intent = intent
        if (Intent.ACTION_VIEW == intent.action) {
            val uri = intent.data
            val code = uri?.getQueryParameter("code") ?: ""
            code.takeIf { it.isNotEmpty() }?.let { code ->
                val accessTokenJob = CoroutineScope(Dispatchers.IO).launch {
                    val response = accessTokenDataSource.accessToken(
                        RequestAccessToken(
                            CLIENT_ID,
                            CLIENT_SECRET,
                            code,
                            REDIRECT_URI,
                            "0"
                        )
                    ).await()

                    tokenRepository.saveToken(response.accessToken).await()
                }

                accessTokenJob.invokeOnCompletion {
                    CoroutineScope(Dispatchers.Main).launch {
                        token.text = tokenRepository.readToken().await()
                        this.cancel()
                        accessTokenJob.cancelAndJoin()
                    }
                }
            } ?: run { finish() }
        }
    }
}