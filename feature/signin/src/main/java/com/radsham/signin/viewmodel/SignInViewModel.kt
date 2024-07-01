package com.radsham.signin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.core_api.listener.UserSignInListener
import com.radsham.signin.repository.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val signInRepository: SignInRepository
) : ViewModel() {

    fun signInUser(email: String, password: String, userSignInListener: UserSignInListener) =
        viewModelScope.launch {
            signInRepository.signInUser(email, password, userSignInListener)
        }
}