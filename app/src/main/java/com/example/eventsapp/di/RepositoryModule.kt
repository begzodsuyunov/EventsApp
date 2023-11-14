package com.example.eventsapp.di

import com.example.eventsapp.repository.AppRepository
import com.example.eventsapp.repository.impl.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAppRepository(impl: AppRepositoryImpl) : AppRepository
}