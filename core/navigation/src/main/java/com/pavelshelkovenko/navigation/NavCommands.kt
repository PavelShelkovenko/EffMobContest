package com.pavelshelkovenko.navigation

import android.net.Uri

sealed class NavCommands {

    data class Browser(val url: String) : NavCommands()

    data class DeepLink(
        val url: Uri,
        val isSingleTop: Boolean = false
    ) : NavCommands()
}