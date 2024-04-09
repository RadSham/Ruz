package com.radsham.ruz

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radsham.ruz.model.EventEntity

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    eventsList: List<EventEntity>,
    showTopBar: MutableState<Boolean>,
) {
    NavHost(navController, startDestination = NavScreen.SplashScreen.route) {
        composable(NavScreen.SplashScreen.route) {
            SplashScreen(navController, showTopBar)
        }
        composable(NavScreen.EventsScreen.route) {
            EventsScreen(paddingValues = paddingValues, eventsList = eventsList, showTopBar)
        }

    }
}