package com.radsham.ruz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.radsham.core_api.NavScreen
import com.radsham.home.MainScreen
import com.radsham.home.MainViewModel
import com.radsham.newevent.NewEventScreen
import com.radsham.splash.SplashScreen

@Composable
fun SetupNavGraph(
    viewModel: MainViewModel,
    showTopBar: MutableState<Boolean>,
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavScreen.SPLASH_SCREEN) {
        composable(NavScreen.SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(NavScreen.MAIN_SCREEN) {
            MainScreen(navController, viewModel,showTopBar)
        }
        composable(NavScreen.NEW_EVENT_SCREEN) {
            NewEventScreen(navController, showTopBar)
        }
    }
}