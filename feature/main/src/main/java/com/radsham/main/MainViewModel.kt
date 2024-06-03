package com.radsham.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.common.Result
import com.radsham.core.FirebaseRepository
import com.radsham.main.model.EventEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<Result<List<EventEntity>>>(Result.Loading("Loading"))
    val viewState = _viewState.asStateFlow()

    fun fetchEventsList() = viewModelScope.launch {
        firebaseRepository.listenForEvents()
            .catch { _viewState.value = Result.Error(it) }
            .collect { stringsList ->
                val allEventsList =
                    List(10) {
                        EventEntity(
                            stringsList[0],
                            stringsList[1],
                            stringsList[2],
                            stringsList[3]
                        )
                    }
                _viewState.emit(Result.Success(allEventsList))
            }
    }
}