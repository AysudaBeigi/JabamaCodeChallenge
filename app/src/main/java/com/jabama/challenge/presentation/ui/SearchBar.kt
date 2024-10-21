package com.jabama.challenge.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    keyword: String,
    onValueChange: (String) -> Unit,
) {
    val onSearchImeAction: (KeyboardActionScope) -> Unit = remember(keyword) {
        {
            onValueChange(keyword)
        }
    }
    TextField(
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        ),
        shape = CircleShape,
        value = keyword,
        maxLines = 1,
        modifier = modifier.fillMaxWidth().height(40.dp),
        isError = false,
        onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = onSearchImeAction,
        ),
    )
}

@Preview
@Composable
fun SearchBarPreview(){

    MaterialTheme {
        SearchBar(
            onValueChange = {},
            modifier = Modifier,
            keyword = "hi"
        )
    }
}