package com.radsham.eventdetails

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.home.ui.EventDetailsScreen
import javax.inject.Inject

class EventDetailsFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(
            NavScreen.EVENT_DETAILS_SCREEN + "/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.StringType})
        ) { backStackEntry ->
            EventDetailsScreen(navController = navController, backStackEntry.arguments?.getString("eventId"))
        }
    }
}