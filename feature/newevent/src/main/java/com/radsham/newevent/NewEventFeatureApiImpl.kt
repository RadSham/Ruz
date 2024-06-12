package com.radsham.newevent

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.newevent.ui.NewEventScreen
import javax.inject.Inject

class NewEventFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(NavScreen.NEW_EVENT_SCREEN) {
            NewEventScreen(navController = navController)
        }
    }
}