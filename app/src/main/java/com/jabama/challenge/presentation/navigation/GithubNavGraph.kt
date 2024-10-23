package com.jabama.challenge.presentation.navigation

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jabama.challenge.presentation.ui.AuthorizeScreen
import com.jabama.challenge.presentation.ui.RepositoriesScreen
import com.jabama.challenge.presentation.viewmodel.AuthorizeViewModel
import com.jabama.challenge.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.compose.getViewModel

private const val CODE = "code"

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
            startDestination = GithubNavigation.AuthorizeScreen.createRoute()
        ) {
            composable(route = GithubNavigation.AuthorizeScreen.createRoute()) {
                val context = LocalContext.current
                val currentIntent = (context as Activity).intent
                val authorizeViewModel = getViewModel<AuthorizeViewModel>()
                LaunchedEffect(currentIntent) {
                    currentIntent.data?.let {
                        getTokenAndHandleDeepLink(
                            uri = it,
                            authorizeViewModel = authorizeViewModel,
                            navController = navController,
                        )
                    }
                }
                AuthorizeScreen(onAuthorizeClick = remember(context) {
                    {
                        authorizeViewModel.authorize(context = context)
                    }
                })
            }
            composable(route = GithubNavigation.RepositoryListScreen.createRoute()) {
                val viewModel = getViewModel<RepositoryViewModel>()
                val state = viewModel.state.collectAsStateWithLifecycle().value
                val keyword = remember {
                    mutableStateOf("")
                }
                RepositoriesScreen(
                    keyword = keyword.value,
                    modifier = Modifier,
                    loadableRepositories = state.loadableRepositories,
                    onSearchValueChange = remember(keyword) {
                        {
                            keyword.value = it
                            viewModel.searchRepository(keyword = keyword.value)
                        }
                    },
                )
            }
        }
    }
}


private fun getTokenAndHandleDeepLink(
    uri: Uri,
    authorizeViewModel: AuthorizeViewModel,
    navController: NavHostController
) {
    uri.getQueryParameter(CODE)?.let {
        authorizeViewModel.getAccessTokenAndSave(code = it)
        navController.navigate(GithubNavigation.RepositoryListScreen.createRoute())
    }
}
