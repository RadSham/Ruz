package com.radsham.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val defaultModifier = Modifier
    .fillMaxSize()
    .padding(all = 128.dp)

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
    )
}