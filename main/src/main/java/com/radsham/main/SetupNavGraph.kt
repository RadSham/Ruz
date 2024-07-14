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
        featureProvider[NavScreen.SIGN_UP_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.ACCOUNT_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.SIGN_IN_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.CHECKUSER_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
        featureProvider[NavScreen.IAMIN_SCREEN]?.registerGraph(
            this,
            navController,
            paddingValues,
            showBottomNavigationBarListener
        )
    }
}