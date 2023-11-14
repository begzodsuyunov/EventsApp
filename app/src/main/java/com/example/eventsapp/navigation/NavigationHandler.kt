package com.example.eventsapp.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.SharedFlow


typealias NavArgs = NavController.() -> Unit


interface NavigationHandler {

    val navStack: SharedFlow<NavArgs>

}