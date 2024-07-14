package com.radsham.account.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.radsham.account.R
import com.radsham.core_api.NavScreen
import com.radsham.core_api.model.User

@Composable
fun PersonalAccount(
    navController: NavHostController,
    user: User,
) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(text = user.nickname)
        Text(text = user.email)
        Button(onClick = {
            navController.navigate(NavScreen.NEW_EVENT_SCREEN) {
                launchSingleTop = true
            }
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "drawable icons",
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.create_new_event),
                textAlign = TextAlign.Center
            )
        }
    }
}

