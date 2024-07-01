package com.radsham.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.home.ui.HomeScreen
import javax.inject.Inject

class HomeFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        paddingValues: PaddingValues
    ) {
        navGraphBuilder.composable(NavScreen.HOME_SCREEN) {
            HomeScreen(navController = navController, paddingValues)
        }
    }
}