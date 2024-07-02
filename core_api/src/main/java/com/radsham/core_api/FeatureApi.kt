package com.radsham.core_api

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.radsham.core_api.listener.ShowBottomNavigationBarListener

interface FeatureApi {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        paddingValues: PaddingValues,
        showBottomNavigationBarListener: ShowBottomNavigationBarListener
    )
}