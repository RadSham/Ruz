package com.radsham.signin.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.radsham.core_api.listener.ShowBottomNavigationBarListener
import com.radsham.signin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavHostController,
    mainPaddingValues: PaddingValues,
    showBottomNavigationBarListener: ShowBottomNavigationBarListener
) {
    showBottomNavigationBarListener.showBar(false)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.sign_in))
                }/*,
                navigationIcon = {
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
            .fillMaxSize()
    ) { paddingValues ->
        SignIn(paddingValues, navController)
    }
}