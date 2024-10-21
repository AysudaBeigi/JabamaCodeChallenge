package com.jabama.challenge.presentation.navigation


sealed class GithubNavigation(private val navigationName: String) {
    companion object {
        const val routeName = "github"
    }

    data object AuthorizeScreen : GithubNavigation("Authorize")
    data object LoginScreen : GithubNavigation("login")

    fun createRoute(): String {
        return "$routeName/$navigationName"
    }
}