package com.example.eventsapp.repository.impl

import com.example.eventsapp.data.room.dao.EventDao
import com.example.eventsapp.data.room.entity.EventEntity
import com.example.eventsapp.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val eventDao: EventDao
):  AppRepository {
    override suspend fun insertEventData(eventEntity: EventEntity) =
        eventDao.insertEvent(eventEntity)

    override suspend fun updateEventData(eventEntity: EventEntity) =
        eventDao.updateEvent(eventEntity)

    override fun getAllEvents(): Flow<List<EventEntity>> =
        eventDao.getAllEvents()
}