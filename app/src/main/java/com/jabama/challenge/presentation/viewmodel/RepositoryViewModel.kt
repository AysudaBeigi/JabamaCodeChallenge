package com.jabama.challenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.base.CoroutineDispatcherProvider
import com.jabama.challenge.base.Failed
import com.jabama.challenge.base.LoadableData
import com.jabama.challenge.base.Loaded
import com.jabama.challenge.base.Loading
import com.jabama.challenge.base.NotLoaded
import com.jabama.challenge.domain.model.Repository
import com.jabama.challenge.domain.usecase.GetRepositoryListUseCase
import com.jabama.challenge.domain.usecase.ReadTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepositoryViewModel(
    private val readTokenUseCase: ReadTokenUseCase,
    private val getRepositoryListUseCase: GetRepositoryListUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : ViewModel() {

    private val _state: MutableStateFlow<RepositoryState> = MutableStateFlow(RepositoryState())
    private var totalRepositoryList: List<Repository> = listOf()
    val state: StateFlow<RepositoryState> = _state.asStateFlow()

    data class RepositoryState(
        val loadableToken: LoadableData<String> = NotLoaded,
        val loadableRepositories: LoadableData<List<Repository>> = NotLoaded,
    )

    init {
        loadRepositoryList()
    }

    private fun loadRepositoryList() {
        viewModelScope.launch(coroutineDispatcherProvider.ioDispatcher()) {
            _state.update {
                it.copy(loadableToken = Loading)
            }
            runCatching {
                readTokenUseCase.execute()
            }.onSuccess { token ->
                _state.update {
                    it.copy(loadableToken = Loaded(token))
                }
                getRepositories(token)
            }.onFailure { throwable ->
                _state.update {
                    it.copy(loadableToken = Failed(throwable))
                }
            }
        }
    }

    private fun getRepositories(token: String) {
        viewModelScope.launch(coroutineDispatcherProvider.ioDispatcher()) {
            _state.update {
                it.copy(loadableRepositories = Loading)
            }
            runCatching {
                getRepositoryListUseCase.execute(token = token)
            }.onSuccess { repositoryList ->
                _state.update {
                    it.copy(loadableRepositories = Loaded(repositoryList))
                }
                totalRepositoryList = repositoryList
            }.onFailure { throwable ->
                _state.update {
                    it.copy(loadableRepositories = Failed(throwable))
                }
            }

        }
    }

    fun searchRepository(keyword: String) {
        _state.update {
            _state.value.loadableRepositories.data?.filter {
                it.name.contains(keyword)
            }?.let { result -> it.copy(loadableRepositories = Loaded(result)) } ?: run {
                it.copy(loadableRepositories = Loaded(totalRepositoryList))
            }
        }

    }

}