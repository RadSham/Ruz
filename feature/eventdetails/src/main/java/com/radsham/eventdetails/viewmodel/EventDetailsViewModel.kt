package com.radsham.eventdetails.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
import com.radsham.core_api.model.User
import com.radsham.eventdetails.repository.EventDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val eventDetailsRepository: EventDetailsRepository,
) : ViewModel() {
    private val _viewState = MutableStateFlow<Result<EventEntity>>(Result.Loading("Loading"))
    val viewState = _viewState.asStateFlow()

    private val _currentUserState = mutableStateOf(User("null", "", ""))
    val currentUserState: State<User>
        get() = _currentUserState

    fun getCurrentUser() = viewModelScope.launch {
        _currentUserState.value = eventDetailsRepository.getCurrentUser()
    }

    fun addParticipant(eventEntityId: String, participantUid: String) = viewModelScope.launch {
        eventDetailsRepository.addParticipant(eventEntityId, participantUid)
    }


    fun fetchEvent(eventId: String) = viewModelScope.launch {
        eventDetailsRepository.listenForEvent(eventId).catch { _viewState.value = Result.Error(it) }
            .collect {
                _viewState.emit(Result.Success(it))
            }
    }
}