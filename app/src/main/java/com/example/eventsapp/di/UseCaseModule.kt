package com.example.eventsapp.di

import com.example.eventsapp.domain.AppUseCase
import com.example.eventsapp.domain.impl.AppUseCaseImpl
import com.example.eventsapp.repository.impl.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

   @Binds
   fun bindUseCase(impl: AppUseCaseImpl) : AppUseCase
}