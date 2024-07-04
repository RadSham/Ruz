package com.radsham.account.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.radsham.account.viewmodel.AccountViewModel
import com.radsham.auth.R
import com.radsham.core_api.NavScreen
import com.radsham.core_api.Result
import com.radsham.core_api.listener.ShowBottomNavigationBarListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavHostController,
    mainPaddingValues: PaddingValues,
    showBottomNavigationBarListener: ShowBottomNavigationBarListener
) {
    showBottomNavigationBarListener.showBar(true)
    val viewModel: AccountViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = "viewModel1", block = { viewModel.getCurrentUser() })
    LaunchedEffect(key1 = "viewModel2", block = { viewModel.getUserEventsList() })

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Personal account")
            }/*, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "ArrowBack"
                    )
                }
            }*/
        )
    },
        modifier = Modifier
            .padding(mainPaddingValues)
            .fillMaxSize(),

        bottomBar = {
            Row(
                modifier = Modifier
                    .border(border = BorderStroke(width = 1.dp, Color.LightGray))
                    .fillMaxWidth()
                    .clickable {
                        viewModel.userSignOut()
                        navController.navigate(NavScreen.HOME_SCREEN) {
                            popUpTo(NavScreen.HOME_SCREEN){
                                saveState = false
                            }
                            launchSingleTop = true
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = R.string.sign_out)
                )
                Icon(
                    modifier = Modifier.padding(10.dp),
                    imageVector = Icons.AutoMirrored.Rounded.ExitToApp,
                    contentDescription = stringResource(id = R.string.sign_out)
                )
            }
        }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column {
                PersonalAccount(
                    navController, viewModel.currentUserState.value
                )
                when (val state = viewState) {
                    is Result.Loading -> {
                        LoadingState(paddingValues)
                    }

                    is Result.Success -> {
                        UserEvents(navController, state.data) { deleteEventId ->
                            viewModel.deleteEvent(deleteEventId)
                        }
                    }

                    is Result.Error -> Toast.makeText(
                        viewModel.appContext, state.exception.message, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}