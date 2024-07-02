package com.example.checkuser.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkuser.repository.CheckUserRepository
import com.radsham.core_api.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckUserViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val checkUserRepository: CheckUserRepository
) : ViewModel() {

    private val _currentUserState = mutableStateOf(User("", "", ""))
    val currentUserState: State<User>
        get() = _currentUserState

    fun getCurrentUser() = viewModelScope.launch {
        _currentUserState.value = checkUserRepository.getCurrentUser()
    }

}