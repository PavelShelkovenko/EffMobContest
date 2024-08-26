package com.pavelshelkovenko.feature_vacancy_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_vacancy_details.databinding.FragmentVacancyDetailsBinding

class VacancyDetails: Fragment(R.layout.fragment_vacancy_details), MenuProvider {

    private val binding: FragmentVacancyDetailsBinding by viewBinding(FragmentVacancyDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        val vacancyId = arguments?.getString(VACANCY_ID_KEY)
        vacancyId?.let {

        }
    }

    private fun setupToolbar() {
        with(requireActivity() as AppCompatActivity){
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = null
            addMenuProvider(this@VacancyDetails, viewLifecycleOwner)
        }
    }


    companion object {
        const val VACANCY_ID_KEY: String = "vacancy_id"
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
           /* R.id.my_menu_item -> {
                // Handle menu item selection
                true
            }*/
            else -> false
        }
    }
}