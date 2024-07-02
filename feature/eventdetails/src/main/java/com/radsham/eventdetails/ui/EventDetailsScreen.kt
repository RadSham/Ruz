package com.radsham.home.ui

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.radsham.eventdetails.R
import com.radsham.eventdetails.ui.EventDetails
import com.radsham.eventdetails.ui.LoadingState
import com.radsham.eventdetails.viewmodel.EventDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(
    navController: NavHostController,
    mainPaddingValues: PaddingValues,
    eventId: String?,
    showBottomNavigationBarListener: ShowBottomNavigationBarListener,
) {
    showBottomNavigationBarListener.showBar(false)
    val viewModel: EventDetailsViewModel = hiltViewModel()

    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = viewModel, block = {
        if (eventId != null) {
            viewModel.fetchEvent(eventId)
        }
    })
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.event_details))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .padding(mainPaddingValues)
            .fillMaxWidth()
    ) { paddingValues ->
        when (val state = viewState) {
            is Result.Loading -> LoadingState(paddingValues)
            is Result.Success -> {
                EventDetails(paddingValues, state.data)
            }

            is Result.Error -> Toast.makeText(
                viewModel.appContext,
                state.exception.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
