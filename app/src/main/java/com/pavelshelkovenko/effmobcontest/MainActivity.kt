package com.pavelshelkovenko.effmobcontest

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.effmobcontest.databinding.ActivityMainBinding
import com.pavelshelkovenko.navigation.NavCommand
import com.pavelshelkovenko.navigation.NavCommands
import com.pavelshelkovenko.navigation.NavigationProvider


class MainActivity : AppCompatActivity(R.layout.activity_main), NavigationProvider {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val navController: NavController
        get() = findNavController(R.id.main_activity_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavCommands.Browser -> openBrowser(target.url)
            is NavCommands.DeepLink -> {
                val isSingleTop = target.isSingleTop
                val navOptions = NavOptions.Builder()
                    .setLaunchSingleTop(isSingleTop)
                    .setPopUpTo(if (isSingleTop) R.id.nav_graph_application else -1, inclusive = isSingleTop)
                    .build()
                navController.navigate(target.url, navOptions)
            }
        }

    }

    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        browserIntent.setPackage("com.android.chrome")
        try {
            this.startActivity(browserIntent)
        } catch (ex: ActivityNotFoundException) {
            browserIntent.setPackage(null)
            this.startActivity(browserIntent)
        }
    }
}