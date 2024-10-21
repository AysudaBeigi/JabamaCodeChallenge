package com.jabama.challenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.domain.usecase.GetAccessTokenUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

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
}