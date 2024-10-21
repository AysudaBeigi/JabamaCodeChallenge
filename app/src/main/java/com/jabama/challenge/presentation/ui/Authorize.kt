package com.jabama.challenge.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
internal fun Authorize(modifier: Modifier = Modifier, onAuthorizeClick: () -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = onAuthorizeClick, contentPadding = PaddingValues(16.dp), content = {
            Text(
                text = "it", style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ), modifier = Modifier, textAlign = TextAlign.Start
            )
        })
    }
}
