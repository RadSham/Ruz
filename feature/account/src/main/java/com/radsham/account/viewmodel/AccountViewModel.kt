package com.radsham.account.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.account.repository.AccountRepository
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
import com.radsham.core_api.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    @ApplicationContext val appContext: Context, private val accountRepository: AccountRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<Result<List<EventEntity>>>(Result.Loading("Loading"))
    val viewState = _viewState.asStateFlow()

    private val _currentUserState = mutableStateOf(User("", "", ""))
    val currentUserState: State<User>
        get() = _currentUserState

    fun getCurrentUser() = viewModelScope.launch {
        _currentUserState.value = accountRepository.getCurrentUser()
    }

    fun getUserEventsList() = viewModelScope.launch {
        accountRepository.getUserEventsList().catch { _viewState.value = Result.Error(it) }
            .collect {
                _viewState.emit(Result.Success(it))
            }
    }

    fun deleteEvent(deleteEventId: String) = viewModelScope.launch {
        accountRepository.deleteEvent(deleteEventId)
    }

    fun userSignOut()= viewModelScope.launch {
        accountRepository.userSignOut()
    }
}