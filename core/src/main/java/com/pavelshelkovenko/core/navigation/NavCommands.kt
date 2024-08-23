package com.pavelshelkovenko.core.navigation

import android.net.Uri

sealed class NavCommands {
    data class DeepLink(
        val url: Uri,
        val isModal: Boolean,
        val isSingleTop: Boolean = false
    ) : NavCommands()
}