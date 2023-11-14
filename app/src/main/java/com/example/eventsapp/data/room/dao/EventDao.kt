package com.example.eventsapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eventsapp.data.room.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(eventEntity: EventEntity)

    @Update
    suspend fun updateEvent(eventEntity: EventEntity)

    @Query("SELECT*FROM event_data")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Query("SELECT*FROM event_data WHERE id=:id")
    suspend fun getEventById(id: Int): EventEntity

}