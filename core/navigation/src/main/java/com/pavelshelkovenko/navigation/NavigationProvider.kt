package com.pavelshelkovenko.navigation

interface NavigationProvider {
    fun launch(navCommand: NavCommand)
}