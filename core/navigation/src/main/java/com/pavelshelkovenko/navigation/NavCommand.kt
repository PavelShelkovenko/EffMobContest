package com.pavelshelkovenko.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavOptions


data class NavCommand(
    val target: DeepLinkNavCommand,
    var args: Bundle? = null,
    val navOptions: NavOptions? = null
)

data class DeepLinkNavCommand(
    val url: Uri,
    val isSingleTop: Boolean = false
)