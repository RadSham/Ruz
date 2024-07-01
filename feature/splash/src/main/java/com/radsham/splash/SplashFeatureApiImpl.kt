package com.radsham.splash

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.splash.ui.SplashScreen
import javax.inject.Inject
class SplashFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        paddingValues: PaddingValues
    ) {
        navGraphBuilder.composable(NavScreen.SPLASH_SCREEN) {
            SplashScreen(navController = navController)
        }
    }
}