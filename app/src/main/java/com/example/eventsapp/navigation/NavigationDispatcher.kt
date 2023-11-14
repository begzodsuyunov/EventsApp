package com.example.eventsapp.navigation

import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class NavigationDispatcher @Inject constructor() : Navigator, NavigationHandler{

    override val navStack = MutableSharedFlow<NavArgs>()

    override suspend fun navigateTo(direction: NavDirections) = navigate {
        navigate(direction)
    }

    override suspend fun navigationUp() {

    }

    private suspend fun navigate(navArgs: NavArgs) {
        navStack.emit(navArgs)
    }
}