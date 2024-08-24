package com.pavelshelkovenko.feature_search_vacancies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_search_vacancies.databinding.FragmentSearchVacanciesBinding

class SearchVacanciesFragment: Fragment(R.layout.fragment_search_vacancies) {

    private val binding: FragmentSearchVacanciesBinding by viewBinding(FragmentSearchVacanciesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTv.setOnClickListener {
            findNavController().navigate(R.id.action_searchVacanciesFragment_to_vacancyInComplianceFragment)
        }
    }
}