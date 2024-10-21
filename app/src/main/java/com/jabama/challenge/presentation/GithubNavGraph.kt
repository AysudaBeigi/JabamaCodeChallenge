package com.jabama.challenge.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jabama.challenge.presentation.ui.Authorize
import org.koin.androidx.compose.getViewModel


@Composable
internal fun GithubNavGraph() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = GithubInformationNavigation.AuthorizeScreen.route
        ) {
            composable(route = GithubInformationNavigation.AuthorizeScreen.route) {
                val githubViewModel = getViewModel<GithubViewModel>()
                Authorize (onAuthorizeClick = {
                    githubViewModel.authorize()
                })
            }
            composable(route = GithubInformationNavigation.LoginScreen.route) {

            }
        }
    }
}

sealed class GithubInformationNavigation(val route: String) {
    object AuthorizeScreen : GithubInformationNavigation("Authorize")
    object LoginScreen : GithubInformationNavigation("login")

}

