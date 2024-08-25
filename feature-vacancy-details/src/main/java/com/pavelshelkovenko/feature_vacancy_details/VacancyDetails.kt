package com.pavelshelkovenko.feature_vacancy_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_vacancy_details.databinding.FragmentVacancyDetailsBinding

class VacancyDetails: Fragment(R.layout.fragment_vacancy_details) {

    private val binding: FragmentVacancyDetailsBinding by viewBinding(FragmentVacancyDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vacancyId = arguments?.getString(VACANCY_ID_KEY)
        vacancyId?.let {

        }
    }

    companion object {
        const val VACANCY_ID_KEY: String = "vacancy_id"
    }
}