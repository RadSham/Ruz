package com.example.checkuser

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.checkuser.ui.CheckUserScreen
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.core_api.listener.ShowBottomNavigationBarListener
import javax.inject.Inject

class CheckUserFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        paddingValues: PaddingValues,
        showBottomNavigationBarListener: ShowBottomNavigationBarListener
    ) {
        navGraphBuilder.composable(NavScreen.CHECKUSER) {
            CheckUserScreen(navController, paddingValues, showBottomNavigationBarListener)
        }
    }
}