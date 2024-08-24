package com.pavelshelkovenko.feature_search_vacancies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_search_vacancies.databinding.FragmentVacancyInComplianceBinding

class VacancyInComplianceFragment : Fragment(R.layout.fragment_vacancy_in_compliance) {
    private val binding: FragmentVacancyInComplianceBinding by viewBinding(FragmentVacancyInComplianceBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filterIcon.setOnClickListener {
            findNavController().navigate(R.id.action_vacancyInComplianceFragment_to_vacancyDetails)
        }
    }
}