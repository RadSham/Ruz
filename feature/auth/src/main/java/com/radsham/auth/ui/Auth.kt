package com.radsham.auth.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun Auth(paddingValues: PaddingValues, navController: NavHostController) {
    Box(modifier = Modifier.padding(paddingValues))
}