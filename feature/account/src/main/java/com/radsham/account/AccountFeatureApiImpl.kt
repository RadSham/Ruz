package com.radsham.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.radsham.account.ui.AccountScreen
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import javax.inject.Inject

class AccountFeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        paddingValues: PaddingValues
    ) {
        navGraphBuilder.composable(NavScreen.ACCOUNT)
        { backStackEntry ->
            AccountScreen(navController, paddingValues)
        }
    }
}