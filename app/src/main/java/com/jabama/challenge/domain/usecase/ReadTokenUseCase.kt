package com.jabama.challenge.domain.usecase

import com.jabama.challenge.domain.ITokenRepository

class ReadTokenUseCase(private val repository: ITokenRepository) {
    suspend fun execute() = repository.readToken()
}