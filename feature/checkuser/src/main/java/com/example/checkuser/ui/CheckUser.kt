package com.example.checkuser.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.checkuser.R
import com.radsham.core_api.NavScreen
import com.radsham.core_api.model.User

@Composable
fun CheckUser(
    mainPaddingValues: PaddingValues,
    navController: NavHostController,
    currentUser: User
) {
    Column(
        modifier = Modifier
            .padding(mainPaddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (currentUser.uid == "null") {
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                navController.navigate(NavScreen.SIGN_IN) {
                    popUpTo(NavScreen.CHECKUSER) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text(text = stringResource(id = R.string.sign_in))
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                navController.navigate(NavScreen.AUTH) {
                    popUpTo(NavScreen.CHECKUSER) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text(text = stringResource(id = R.string.sign_up))
            }
        } else {
            navController.navigate(NavScreen.ACCOUNT) {
                popUpTo(NavScreen.CHECKUSER) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}