package com.example.eventsapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eventsapp.data.room.dao.EventDao
import com.example.eventsapp.data.room.entity.EventEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [EventEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    init {
        val list1 = listOf(
            "Wi-Fi включен ",
            "Wi-Fi выключен",
            "Полная заряд батарея вкличен",
            "Низкий заряд батарея вкличен",
            "Режим полета включен",
            "Режим полета выключен",
            "Bluetooth включен",
            "Bluetooth выключен",
            "Экран включен",
            "Экран выключен",
            "Зарядка включен",
            "Зарядка выключен",
            "Наушник подключён",
            "Наушник отключён"
        )
        val list = listOf(
            "Вы включили Wi-Fi",
            "Вы выключили Wi-Fi",
            "Полная батарея",
            "Низкий заряд батареи",
            "Вы включили режим полета",
            "Вы выключили режим полета",
            "Вы включили bluetooth",
            "Вы выключили bluetooth",
            "Вы включили экран",
            "Вы выключили экран",
            "Вы подключили зарядку",
            "Вы отключили зарядку",
            "Вы подключили наушники",
            "Вы отключили наушники"
        )
        GlobalScope.launch {
            for (i in list.indices) {
                val voice = list1[i]
                val name = list[i]
                eventDao().insertEvent(EventEntity(0, name, voice, 0))
            }
        }
    }
}