package com.pavelshelkovenko.effmobcontest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.navigation.NavCommand
import com.pavelshelkovenko.navigation.NavigationProvider
import com.pavelshelkovenko.effmobcontest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main), NavigationProvider {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val navController: NavController
        get() = findNavController(R.id.main_activity_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(binding.root)
        //applyInsets()
    }



    private fun applyInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }

    override fun launch(navCommand: NavCommand) {
        val isSingleTop = navCommand.target.isSingleTop
        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(isSingleTop)
            .setPopUpTo(if (isSingleTop) R.id.nav_graph_application else -1, inclusive = isSingleTop)
            .build()
        navController.navigate(navCommand.target.url, navOptions)
    }
}