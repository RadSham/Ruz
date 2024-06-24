package com.radsham.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen

@Composable
fun SetupNavGraph(featureProvider: Map<String, @JvmSuppressWildcards FeatureApi>) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavScreen.SPLASH_SCREEN) {
        featureProvider[NavScreen.SPLASH_SCREEN]?.registerGraph(this, navController)
        featureProvider[NavScreen.HOME_SCREEN]?.registerGraph(this, navController)
        featureProvider[NavScreen.NEW_EVENT_SCREEN]?.registerGraph(this, navController)
        featureProvider[NavScreen.EVENT_DETAILS_SCREEN]?.registerGraph(this, navController)
        featureProvider[NavScreen.AUTH]?.registerGraph(this, navController)
    }
}