package com.jabama.challenge.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.base.CoroutineDispatcherProvider
import com.jabama.challenge.base.Failed
import com.jabama.challenge.base.LoadableData
import com.jabama.challenge.base.Loaded
import com.jabama.challenge.base.Loading
import com.jabama.challenge.base.NotLoaded
import com.jabama.challenge.domain.model.RequestAccessToken
import com.jabama.challenge.domain.model.ResponseAccessToken
import com.jabama.challenge.domain.usecase.GetAccessTokenUseCase
import com.jabama.challenge.domain.usecase.SaveTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val CLIENT_ID = "Ov23lieNfZen0Ac2lX66"
private const val CLIENT_SECRET = "b49541b80e0b3db47a8e5a785749f1fdf32baa27"
private const val REDIRECT_URI = "https://github.com/AysudaBeigi/JabamaCodeChallenge"
private const val url =
    "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=0"

private const val CODE = "code"

class GithubViewModel(
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : ViewModel() {

    private val _state: MutableStateFlow<GithubState> = MutableStateFlow(GithubState())

    data class GithubState(
        val loadableToken: LoadableData<ResponseAccessToken> = NotLoaded,
    )

    init {
        Uri.parse(url).getQueryParameter(CODE)?.let {
            authorize(it)
        }
    }

    fun authorize(code: String) {
        viewModelScope.launch(coroutineDispatcherProvider.ioDispatcher()) {
            _state.update {
                it.copy(loadableToken = Loading)
            }
            runCatching {
                getAccessTokenUseCase.execute(
                    requestAccessToken = RequestAccessToken(
                        code = code,
                        state = "0",
                        clientSecret = CLIENT_SECRET,
                        clientId = CLIENT_ID,
                        redirectUri = REDIRECT_URI,
                    )
                )

            }.onSuccess { token ->
                _state.update {
                    it.copy(loadableToken = Loaded(token))
                }
                saveTokenUseCase.execute(token = token.accessToken)
            }.onFailure { throwable ->
                _state.update {
                    it.copy(loadableToken = Failed(throwable))
                }
            }

        }
    }
}