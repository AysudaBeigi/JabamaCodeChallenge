package com.jabama.challenge.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("isFavorite")
    var isFavorite: Boolean,
    @SerializedName("stargazers_count")
    val stargazers_count: Int,
    @SerializedName("updated_at")
    val updated_at: String,
    @SerializedName("visibility")
    val visibility: String,
)
