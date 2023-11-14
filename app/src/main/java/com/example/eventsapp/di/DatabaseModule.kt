package com.example.eventsapp.di

import android.content.Context
import androidx.room.Room
import com.example.eventsapp.data.room.AppDatabase
import com.example.eventsapp.data.room.dao.EventDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "event_data.db")
            .build()
    }

    @[Provides Singleton]
    fun provideEventDao(appDatabase: AppDatabase): EventDao =
        appDatabase.eventDao()


}