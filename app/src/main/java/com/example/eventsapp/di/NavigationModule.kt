package com.example.eventsapp.di

import com.example.eventsapp.navigation.NavigationDispatcher
import com.example.eventsapp.navigation.NavigationHandler
import com.example.eventsapp.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindNavigator(navigationDispatcher: NavigationDispatcher): Navigator

    @[Binds Singleton]
    fun bindNavigationHandler(navigationDispatcher: NavigationDispatcher): NavigationHandler

}