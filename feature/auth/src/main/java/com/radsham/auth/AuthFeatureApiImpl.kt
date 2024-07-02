package com.radsham.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.radsham.auth.ui.AuthScreen
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.core_api.listener.ShowBottomNavigationBarListener
import javax.inject.Inject

class AuthFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        paddingValues: PaddingValues,
        showBottomNavigationBarListener: ShowBottomNavigationBarListener
    ) {
        navGraphBuilder.composable(NavScreen.AUTH) {
            AuthScreen(navController, paddingValues, showBottomNavigationBarListener)
        }
    }
}