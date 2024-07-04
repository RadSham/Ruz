package com.radsham.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.core_api.listener.ShowBottomNavigationBarListener

@Composable
fun SetupNavGraph(
    featureProvider: Map<String, @JvmSuppressWildcards FeatureApi>,
    navController: NavHostController,
    paddingValues: PaddingValues,
    showBottomNavigationBarListener: ShowBottomNavigationBarListener
) {
    NavHost(navController, startDestination = NavScreen.SPLASH_SCREEN) {
        featureProvider[NavScreen.SPLASH_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.HOME_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.NEW_EVENT_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.EVENT_DETAILS_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.AUTH]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.ACCOUNT]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.SIGN_IN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.CHECKUSER]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.IAMIN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
    }
}