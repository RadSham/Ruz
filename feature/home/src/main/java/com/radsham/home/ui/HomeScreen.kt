package com.radsham.home.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.radsham.core_api.Result
import com.radsham.core_api.listener.ShowBottomNavigationBarListener
import com.radsham.core_api.model.Categories
import com.radsham.core_api.model.names
import com.radsham.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    mainPaddingValues: PaddingValues,
    showBottomNavigationBarListener: ShowBottomNavigationBarListener,
) {
    showBottomNavigationBarListener.showBar(true)
    val viewModel: HomeViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(key1 = "viewModel2", block = { viewModel.fetchAllEvents() })
    val queryName = remember { mutableStateOf("") }
    val categoriesSelectedList = remember { mutableStateListOf<String>() }
    Scaffold(
        modifier = Modifier.padding(mainPaddingValues),
        topBar = {
            Column {
                SearchView(query = queryName)
                LazyRow {
                    items(Categories.entries.names()) { categoryName ->
                        Button(
                            onClick = {
                                if (!categoriesSelectedList.contains(categoryName)) {
                                    categoriesSelectedList.add(categoryName)
                                } else {
                                    categoriesSelectedList.remove(categoryName)
                                }
                            },
                            colors =
                            if (categoriesSelectedList.contains(categoryName)) {
                                ButtonDefaults.buttonColors()
                            } else {
                                ButtonDefaults.buttonColors()
                                    .copy(containerColor = Color(0xFFCCC2DC))
                            },
                            modifier = Modifier.padding(2.dp),
                        ) {
                            Text(
                                text = categoryName,
                                color = if (categoriesSelectedList.contains(categoryName)) {
                                    Color.White
                                } else {
                                    Color.Black
                                }
                            )
                        }
                    }
                }
            }
        }) { paddingValues ->
        when (val state = viewState) {
            is Result.Loading -> LoadingState(paddingValues)
            is Result.Success -> {
                AllEvents(
                    paddingValues = paddingValues,
                    navController = navController,
                    eventsList = state.data,
                    queryName = queryName,
                    categoriesSelectedList = categoriesSelectedList.toList()
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
