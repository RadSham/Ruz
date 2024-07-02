package com.radsham.home.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
import com.radsham.core_api.model.User
import com.radsham.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<Result<List<EventEntity>>>(Result.Loading("Loading"))
    val viewState = _viewState.asStateFlow()

    private val _currentUserState = mutableStateOf(User("", "",""))
    val currentUserState: State<User>
        get() = _currentUserState

    fun getCurrentUser() = viewModelScope.launch {
        _currentUserState.value = homeRepository.getCurrentUser()
    }

    fun fetchAllEvents() = viewModelScope.launch {
        homeRepository.listenForEvents()
            .catch { _viewState.value = Result.Error(it) }
            .collect {
                _viewState.emit(Result.Success(it))
            }
    }
}