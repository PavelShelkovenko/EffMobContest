package com.pavelshelkovenko.effmobcontest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.effmobcontest.databinding.MainFragmentBinding

class MainFragment: Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()
    }


    private fun setupBottomNavigation() {
        val bottomNavigationView = binding.bottomNavigation
        val navHostFragment = childFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }
}