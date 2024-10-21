package com.jabama.challenge.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.jabama.challenge.base.Failed
import com.jabama.challenge.base.LoadableData
import com.jabama.challenge.base.Loaded
import com.jabama.challenge.base.Loading
import com.jabama.challenge.domain.model.Repository
import com.jabama.challenge.github.R

@Composable
fun RepositoriesScreen(
    loadableRepositories: LiveData<LoadableData<List<Repository>>>,
    onStarClicked: (Repository) -> Unit,
    modifier: Modifier = Modifier
) {
    when (val it = loadableRepositories.observeAsState().value) {
        is Failed -> {
            Toast.makeText(LocalContext.current, it.throwble.message, Toast.LENGTH_SHORT).show()
        }
        is Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Gray)
            }
        }
        is Loaded -> {
            RepositoryList(
                list = loadableRepositories.observeAsState().value?.data ?: listOf(),
                onStarClicked = onStarClicked,
                modifier = modifier
            )
        }
        else -> {}
    }
}

@Composable
fun RepositoryList(
    list: List<Repository>,
    onStarClicked: (Repository) -> Unit,
    modifier: Modifier = Modifier,
) {
    val repositoryListState = rememberLazyListState()

    if (list.isEmpty())
        EmptyItem()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        state = repositoryListState, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) { item ->
            RepositoryItem(
                repository = item,
                onStarClicked = { onStarClicked(it) },
                modifier = Modifier
            )
        }
    }
}

@Composable
fun RepositoryItem(
    repository: Repository,
    onStarClicked: (Repository) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember {
        mutableStateOf(repository.isFavorite)
    }

    Column(
        modifier = modifier
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = repository.name, style = TextStyle(
                    color = Color.Blue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = repository.visibility, style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ), modifier = Modifier
                    .border(
                        2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                isFavorite = !isFavorite
                onStarClicked(repository)
            }) {
                Icon(
                    painter = painterResource(
                        id = if (isFavorite) R.drawable.ic_star_fav_24
                        else R.drawable.ic_star
                    ),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = repository.language, style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            if (isFavorite) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_fav_24),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = repository.updated_at, style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
        )
    }
}

@Composable
fun EmptyItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.empty_repository),
            style = TextStyle(color = Color.Gray, fontSize = 16.sp, fontWeight = FontWeight.Normal),
            textAlign = TextAlign.Center,
            modifier = Modifier,
        )
    }
}