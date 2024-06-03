package com.radsham.ruz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.radsham.splash.SplashScreen
import com.radsham.common.NavScreen
import com.radsham.main.MainScreen
import com.radsham.main.MainViewModel

@Composable
fun SetupNavGraph(
    viewModel: MainViewModel,
    showTopBar: MutableState<Boolean>,
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavScreen.SPLASH_SCREEN) {
        composable(NavScreen.SPLASH_SCREEN) {
            SplashScreen(navController, showTopBar)
        }
        composable(NavScreen.MAIN_SCREEN) {
            MainScreen(viewModel,showTopBar)
        }
    }
}