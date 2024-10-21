package com.jabama.challenge.domain.usecase

import com.jabama.challenge.domain.ITokenRepository

class SaveTokenUseCase(private val repository: ITokenRepository) {
    suspend fun execute(token: String) = repository.saveToken(token = token)
}