package com.pavelshelkovenko.core.navigation

interface NavigationProvider {
    fun launch(navCommand: NavCommand)
}