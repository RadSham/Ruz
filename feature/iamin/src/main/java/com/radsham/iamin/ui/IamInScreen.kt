package com.radsham.iamin.ui

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.radsham.core_api.Result
import com.radsham.core_api.listener.ShowBottomNavigationBarListener
import com.radsham.iamin.R
import com.radsham.iamin.viewmodel.IamInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IamInScreen(
    navController: NavHostController,
    mainPaddingValues: PaddingValues,
    showBottomNavigationBarListener: ShowBottomNavigationBarListener
) {
    showBottomNavigationBarListener.showBar(true)
    val viewModel: IamInViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(key1 = "viewModel2", block = { viewModel.fetchIamInEvents() })

    Scaffold(
        modifier = Modifier.padding(mainPaddingValues),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.events_iamin))}/*, navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu, contentDescription = "Menu"
                    )
                }
            }*/)
        }
    ) { paddingValues ->
        when (val state = viewState) {
            is Result.Loading -> LoadingState(paddingValues)
            is Result.Success -> {
                IamInEvents(
                    paddingValues = paddingValues,
                    navController = navController,
                    eventsList = state.data
                )
            }

            is Result.Error -> Toast.makeText(
                viewModel.appContext,
                state.exception.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
