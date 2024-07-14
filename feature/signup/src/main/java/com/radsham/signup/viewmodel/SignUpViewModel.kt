package com.radsham.signup.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.signup.repository.SignUpRepository
import com.radsham.core_api.listener.UserCreateListener
import com.radsham.core_api.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val authRepository: SignUpRepository
) : ViewModel() {

    fun createNewUser(user: User, password: String, userCreateListener: UserCreateListener) = viewModelScope.launch {
        authRepository.createNewUser(user, password, userCreateListener)
    }
}