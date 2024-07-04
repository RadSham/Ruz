package com.radsham.home.ui

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.radsham.core_api.Result
import com.radsham.core_api.listener.ShowBottomNavigationBarListener
import com.radsham.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    mainPaddingValues: PaddingValues,
    showBottomNavigationBarListener: ShowBottomNavigationBarListener
) {
    showBottomNavigationBarListener.showBar(true)
    val viewModel: HomeViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(key1 = "viewModel2", block = { viewModel.fetchAllEvents() })

    val query = remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.padding(mainPaddingValues),
        topBar = { SearchView(query = query) }
    ) { paddingValues ->
        when (val state = viewState) {
            is Result.Loading -> LoadingState(paddingValues)
            is Result.Success -> {
                AllEvents(
                    paddingValues = paddingValues,
                    navController = navController,
                    eventsList = state.data,
                    query = query
                )
            }

            is Result.Error -> Toast.makeText(
                viewModel.appContext,
                state.exception.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
//    }
}
