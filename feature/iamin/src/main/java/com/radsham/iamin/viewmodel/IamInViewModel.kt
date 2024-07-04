package com.radsham.iamin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
import com.radsham.iamin.repository.IamInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IamInViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val iamInRepository: IamInRepository,
) : ViewModel() {
    private val _viewState = MutableStateFlow<Result<List<EventEntity>>>(Result.Loading("Loading"))
    val viewState = _viewState.asStateFlow()

    fun fetchIamInEvents() = viewModelScope.launch {
        iamInRepository.listenForIamInEvents()
            .catch { _viewState.value = Result.Error(it) }
            .collect {
                _viewState.emit(Result.Success(it))
            }
    }
}