package com.example.eventsapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventsapp.data.room.entity.EventEntity
import com.example.eventsapp.domain.AppUseCase
import com.example.eventsapp.navigation.Navigator
import com.example.eventsapp.presentation.viewmodels.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val appUseCase: AppUseCase,
    private val navigator: Navigator
) : MainViewModel, ViewModel() {

    override val allEventData =
        MutableSharedFlow<List<EventEntity>>(replay = 2, onBufferOverflow = BufferOverflow.DROP_LATEST)


    init {
        viewModelScope.launch {
            appUseCase.getAllEvents().collectLatest {
                allEventData.emit(it)
            }
        }
    }


    override fun itemClick(eventEntity: EventEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            appUseCase.updateEventData(eventEntity)
        }
    }

    override fun getAllEvents() {
        viewModelScope.launch {
            appUseCase.getAllEvents().collectLatest {
                allEventData.emit(it)
            }
        }
    }
}