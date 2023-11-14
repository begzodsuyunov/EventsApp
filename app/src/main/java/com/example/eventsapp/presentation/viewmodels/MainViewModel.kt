package com.example.eventsapp.presentation.viewmodels

import com.example.eventsapp.data.room.entity.EventEntity
import kotlinx.coroutines.flow.SharedFlow

interface MainViewModel {

    val allEventData: SharedFlow<List<EventEntity>>

    fun itemClick(eventEntity: EventEntity)

    fun getAllEvents()

}