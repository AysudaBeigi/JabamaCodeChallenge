package com.jabama.challenge.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jabama.challenge.base.Failed
import com.jabama.challenge.base.LoadableData
import com.jabama.challenge.base.Loaded
import com.jabama.challenge.base.Loading
import com.jabama.challenge.base.NotLoaded
import com.jabama.challenge.domain.model.Repository
import com.jabama.challenge.github.R

@Composable
fun RepositoriesScreen(
    modifier: Modifier = Modifier,
    loadableRepositories: LoadableData<List<Repository>>,
    onSearchValueChange: (String) -> Unit,
    keyword: String,
) {
    Column(modifier = modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier,
            onValueChange = onSearchValueChange,
            keyword = keyword,
        )
        Spacer(Modifier.height(32.dp))
        LoadableRepositoryListComponent(loadableRepositories = loadableRepositories)
    }

}

@Composable
private fun LoadableRepositoryListComponent(
    loadableRepositories: LoadableData<List<Repository>>,
    modifier: Modifier = Modifier,
) {
    when (loadableRepositories) {
        is Failed -> {
            Toast.makeText(
                LocalContext.current,
                loadableRepositories.throwble.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        is Loading, NotLoaded -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Gray)
            }
        }

        is Loaded -> {
            LoadedRepositoryList(
                list = loadableRepositories.data,
                modifier = modifier
            )
        }
    }
}

@Composable
fun LoadedRepositoryList(
    list: List<Repository>,
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
                modifier = Modifier
            )
        }
    }
}

@Composable
fun RepositoryItem(
    repository: Repository,
    modifier: Modifier = Modifier
) {
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