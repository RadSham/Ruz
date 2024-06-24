package com.radsham.eventdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
import com.radsham.eventdetails.repository.EventDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val eventDetailsRepository: EventDetailsRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<Result<EventEntity>>(Result.Loading("Loading"))
    val viewState = _viewState.asStateFlow()

    fun fetchEvent(eventId:String) = viewModelScope.launch {
        eventDetailsRepository.listenForEvent(eventId)
            .catch { _viewState.value = Result.Error(it) }
            .collect {
                _viewState.emit(Result.Success(it))
            }
    }
}