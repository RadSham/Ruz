package com.radsham.newevent.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.core_api.model.EventEntity
import com.radsham.newevent.repository.NewEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewEventViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val newEventRepository: NewEventRepository
) : ViewModel() {

    fun sendNewEvent(eventEntity: EventEntity) = viewModelScope.launch {
        newEventRepository.sendNewEvent(appContext, eventEntity)
    }
}
