package com.pavelshelkovenko.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavOptions


data class NavCommand(
    val target: NavCommands,
    var args: Bundle? = null,
    val navOptions: NavOptions? = null
)