package com.jabama.challenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.base.CoroutineDispatcherProvider
import com.jabama.challenge.base.Failed
import com.jabama.challenge.base.LoadableData
import com.jabama.challenge.base.Loaded
import com.jabama.challenge.base.Loading
import com.jabama.challenge.base.NotLoaded
import com.jabama.challenge.domain.model.ResponseAccessToken
import com.jabama.challenge.domain.usecase.GetAccessTokenUseCase
import com.jabama.challenge.domain.usecase.SaveTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AuthorizeViewModel(
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : ViewModel() {

    private val _state: MutableStateFlow<GithubState> = MutableStateFlow(GithubState())
    val state: StateFlow<GithubState> = _state.asStateFlow()

    data class GithubState(
        val loadableToken: LoadableData<ResponseAccessToken> = NotLoaded,
    )

    fun authorize() {
        viewModelScope.launch(coroutineDispatcherProvider.ioDispatcher()) {
            _state.update {
                it.copy(loadableToken = Loading)
            }
            runCatching {
                getAccessTokenUseCase.execute()
            }.onSuccess { token ->
                token?.let {
                    _state.update {
                        it.copy(loadableToken = Loaded(token))
                    }
                    saveTokenUseCase.execute(token = token.accessToken)
                }
            }.onFailure { throwable ->
                _state.update {
                    it.copy(loadableToken = Failed(throwable))
                }
            }

        }
    }
}