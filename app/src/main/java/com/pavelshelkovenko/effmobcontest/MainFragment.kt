package com.pavelshelkovenko.effmobcontest

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.effmobcontest.databinding.MainFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)
    private val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()
        observeState()
    }


    private fun observeState() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.favouriteCount.collect { vacanciesCount ->
                binding.bottomNavigation.getOrCreateBadge(com.pavelshelkovenko.ui.R.id.nav_graph_favorite_vacancies).apply {
                    isVisible = vacanciesCount > 0
                    number = vacanciesCount
                    backgroundColor = ContextCompat.getColor(requireContext(), com.pavelshelkovenko.ui.R.color.basic_red)
                    badgeTextColor = ContextCompat.getColor(requireContext(), com.pavelshelkovenko.ui.R.color.basic_white)
                }
            }
        }

    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = binding.bottomNavigation
        val navHostFragment = childFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }
}