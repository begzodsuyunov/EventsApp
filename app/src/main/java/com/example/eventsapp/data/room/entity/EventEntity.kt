package com.example.eventsapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_data")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val voice:String,
    val status: Int = 0
)
