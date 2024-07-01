package com.radsham.auth.ui

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.radsham.auth.R
import com.radsham.auth.viewmodel.AuthViewModel
import com.radsham.core_api.NavScreen
import com.radsham.core_api.listener.UserCreateListener
import com.radsham.core_api.model.User


@Composable
fun Auth(paddingValues: PaddingValues, navController: NavHostController) {
    Box(modifier = Modifier.padding(paddingValues)) {
        val viewModel: AuthViewModel = hiltViewModel()
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            var nicknameText by remember { mutableStateOf("") }
            var emailText by remember { mutableStateOf("") }
            var passwordText by remember { mutableStateOf("") }
            var isErrorNickname by remember { mutableStateOf(false) }
            var isErrorEmail by remember { mutableStateOf(false) }
            var isErrorPassword by remember { mutableStateOf(false) }

            Row {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = nicknameText,
                    onValueChange = {
                        nicknameText = it
                        isErrorNickname = false
                    },
                    label = { Text(stringResource(R.string.nickname)) },
                    trailingIcon = {
                        if (nicknameText.isNotEmpty()) {
                            IconButton(onClick = { nicknameText = "" }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close, contentDescription = null
                                )
                            }
                        }
                    },
                    maxLines = 1,
                    supportingText = {
                        if (isErrorNickname) {
                            Text(stringResource(R.string.empty_nickname))
                        }
                    },
                    isError = isErrorNickname
                )
            }
            Row {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailText,
                    onValueChange = {
                        emailText = it
                        isErrorEmail = false
                    },
                    label = { Text(stringResource(R.string.email)) },
                    trailingIcon = {
                        if (emailText.isNotEmpty()) {
                            IconButton(onClick = { emailText = "" }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close, contentDescription = null
                                )
                            }
                        }
                    },
                    maxLines = 1,
                    supportingText = {
                        if (isErrorEmail) {
                            Text(stringResource(R.string.invalid_email))
                        }
                    },
                    isError = isErrorEmail
                )
            }
            Row {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordText,
                    onValueChange = {
                        passwordText = it
                        isErrorPassword = false
                    },
                    label = { Text(stringResource(R.string.password)) },
                    trailingIcon = {
                        if (passwordText.isNotEmpty()) {
                            IconButton(onClick = { passwordText = "" }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close, contentDescription = null
                                )
                            }
                        }
                    },
                    maxLines = 1,
                    supportingText = {
                        if (isErrorPassword) {
                            Text(stringResource(R.string.invalid_password))
                        }
                    },
                    isError = isErrorPassword
                )
            }

            fun checkDetails(): Boolean {
                var isAllFilled = true
                if (!emailValidator(emailText)) {
                    isErrorEmail = true
                    isAllFilled = false
                }
                if (passwordText.length < 6) {
                    isErrorPassword = true
                    isAllFilled = false
                }
                if (nicknameText.isEmpty()) {
                    isErrorNickname = true
                    isAllFilled = false
                }
                return isAllFilled
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                if (checkDetails()) {
                    viewModel.createNewUser(User(nicknameText, emailText), passwordText, object :
                        UserCreateListener {
                        override fun onSuccess(user: User) {
                            Toast.makeText(
                                viewModel.appContext,
                                R.string.user_created,
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(NavScreen.HOME_SCREEN) {
                                popUpTo(NavScreen.AUTH) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }

                        override fun onFailure(message: String?) {
                            Toast.makeText(viewModel.appContext, message, Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }) {
                Text(stringResource(R.string.sign_up))
            }
        }
    }
}

fun emailValidator(stringEmail: String): Boolean {
    if (!stringEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(stringEmail).matches()) {
        return true
    } else {
        return false
    }
}