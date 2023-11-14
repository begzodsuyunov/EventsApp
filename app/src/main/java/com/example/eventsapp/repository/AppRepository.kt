package com.example.eventsapp.repository

import com.example.eventsapp.data.room.entity.EventEntity
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun insertEventData(eventEntity: EventEntity)

    suspend fun updateEventData(eventEntity: EventEntity)

    fun getAllEvents(): Flow<List<EventEntity>>
}