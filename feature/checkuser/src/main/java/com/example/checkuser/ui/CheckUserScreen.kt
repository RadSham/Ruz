package com.example.checkuser.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.checkuser.viewmodel.CheckUserViewModel
import com.radsham.core_api.listener.ShowBottomNavigationBarListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckUserScreen(
    navController: NavHostController,
    mainPaddingValues: PaddingValues,
    showBottomNavigationBarListener: ShowBottomNavigationBarListener
) {
    showBottomNavigationBarListener.showBar(true)
    val viewModel: CheckUserViewModel = hiltViewModel()

    LaunchedEffect(key1 = "viewModel", block = { viewModel.getCurrentUser() })

    CheckUser(mainPaddingValues, navController, viewModel.currentUserState.value)
}
