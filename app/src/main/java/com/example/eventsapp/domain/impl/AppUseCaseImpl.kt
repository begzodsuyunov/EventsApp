package com.example.eventsapp.domain.impl

import com.example.eventsapp.data.room.entity.EventEntity
import com.example.eventsapp.domain.AppUseCase
import com.example.eventsapp.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : AppUseCase {
    override suspend fun insertEventData(eventEntity: EventEntity) =
        repository.insertEventData(eventEntity)

    override suspend fun updateEventData(eventEntity: EventEntity) =
        repository.updateEventData(eventEntity)

    override fun getAllEvents(): Flow<List<EventEntity>> =
        repository.getAllEvents()

}