package com.pavelshelkovenko.feature_search_vacancies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.data.models.OffersAndVacancies
import com.pavelshelkovenko.feature_search_vacancies.databinding.FragmentSearchVacanciesBinding
import com.pavelshelkovenko.feature_vacancy_details.VacancyDetails.Companion.VACANCY_ID_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchVacanciesFragment: Fragment(R.layout.fragment_search_vacancies) {

    private val binding: FragmentSearchVacanciesBinding by viewBinding(FragmentSearchVacanciesBinding::bind)
    private var tempResponse: OffersAndVacancies? = null // TODO Для теста, убрать

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTv.setOnClickListener {
            tempResponse?.vacancies?.first()?.id?.let { id ->
                findNavController().navigate(
                    R.id.action_searchVacanciesFragment_to_vacancyDetails,
                    bundleOf(VACANCY_ID_KEY to id)
                )
            }

        }

        lifecycleScope.launch(Dispatchers.IO) {
            val response = com.pavelshelkovenko.data.Repo.getRepo().getOffersAndVacancies()
            tempResponse = response.getOrNull()
        }
    }
}