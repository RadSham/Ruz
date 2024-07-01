package com.radsham.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen

@Composable
fun SetupNavGraph(
    featureProvider: Map<String, @JvmSuppressWildcards FeatureApi>,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController, startDestination = NavScreen.SPLASH_SCREEN) {
        featureProvider[NavScreen.SPLASH_SCREEN]?.registerGraph(this, navController, paddingValues)
        featureProvider[NavScreen.HOME_SCREEN]?.registerGraph(this, navController, paddingValues)
        featureProvider[NavScreen.NEW_EVENT_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues
        )
        featureProvider[NavScreen.EVENT_DETAILS_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues
        )
        featureProvider[NavScreen.AUTH]?.registerGraph(this, navController, paddingValues)
        featureProvider[NavScreen.ACCOUNT]?.registerGraph(this, navController, paddingValues)
        featureProvider[NavScreen.SIGN_IN]?.registerGraph(this, navController, paddingValues)
    }
}