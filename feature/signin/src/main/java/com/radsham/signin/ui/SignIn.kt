package com.radsham.signin.ui

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
import com.radsham.core_api.NavScreen
import com.radsham.core_api.listener.UserSignInListener
import com.radsham.signin.R
import com.radsham.signin.viewmodel.SignInViewModel

@Composable
fun SignIn(paddingValues: PaddingValues, navController: NavHostController) {
    Box(modifier = Modifier.padding(paddingValues)) {
        val viewModel: SignInViewModel = hiltViewModel()
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            var emailText by remember { mutableStateOf("") }
            var passwordText by remember { mutableStateOf("") }
            var isErrorEmail by remember { mutableStateOf(false) }
            var isErrorPassword by remember { mutableStateOf(false) }

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
                return isAllFilled
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                if (checkDetails()) {
                    viewModel.signInUser(emailText, passwordText, object :
                        UserSignInListener {
                        override fun onSuccess() {
                            Toast.makeText(
                                viewModel.appContext,
                                R.string.sign_in_successful,
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(NavScreen.ACCOUNT) {
                                popUpTo(NavScreen.HOME_SCREEN)
                                launchSingleTop = true
                            }
                        }

                        override fun onFailure(message: String?) {
                            Toast.makeText(viewModel.appContext, message, Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }) {
                Text(stringResource(R.string.sign_in))
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