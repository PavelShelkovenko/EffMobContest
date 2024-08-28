package com.pavelshelkovenko.feature_search_vacancies.extended_search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_search_vacancies.R
import com.pavelshelkovenko.feature_search_vacancies.common.VacancyAdapter
import com.pavelshelkovenko.feature_search_vacancies.databinding.FragmentExtendedSearchBinding
import com.pavelshelkovenko.feature_vacancy_details.VacancyDetails.Companion.VACANCY_ID_KEY
import com.pavelshelkovenko.ui.delegate_adapter.BaseAdapter
import com.pavelshelkovenko.ui.extensions.gone
import com.pavelshelkovenko.ui.extensions.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExtendedSearchFragment : Fragment(R.layout.fragment_extended_search) {

    private val binding: FragmentExtendedSearchBinding by viewBinding(FragmentExtendedSearchBinding::bind)
    private val viewModel: ExtendedSearchViewModel by viewModel()

    private val vacancyAdapter by lazy {
        BaseAdapter.Builder()
            .add(VacancyAdapter())
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupClickListeners()
        observeScreenState()
    }

    private fun setupUi() {
        with(binding) {
            vacanciesRv.adapter = vacancyAdapter
            icBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun observeScreenState() {
        viewModel.loadData()
        viewModel.screenState
            .flowWithLifecycle(lifecycle)
            .onEach(::render)
            .launchIn(lifecycleScope)
    }

    private fun render(newScreenState: ExtendedSearchScreenState) {
        with(binding) {
            when (newScreenState) {
                ExtendedSearchScreenState.Initial -> {
                    progressBar.gone()
                    vacanciesRv.gone()
                }
                ExtendedSearchScreenState.Loading -> {
                    progressBar.visible()
                    vacanciesRv.gone()
                }
                is ExtendedSearchScreenState.Content -> {
                    progressBar.gone()
                    vacanciesRv.visible()
                    vacancyAdapter.submitList(newScreenState.vacancies)
                    vacancyCount.text = resources.getQuantityString(
                        com.pavelshelkovenko.ui.R.plurals.extended_search_vacancy_count,
                        newScreenState.vacancies.size,
                        newScreenState.vacancies.size,
                    )
                }
            }
        }
    }

    private fun setupClickListeners() {
        val adapter = vacancyAdapter.delegates.get(0) as VacancyAdapter

        with(adapter) {
            onVacancyClickListener = { vacancyId ->
                findNavController().navigate(
                    R.id.action_extendedSearchFragment_to_vacancyDetails,
                    bundleOf(VACANCY_ID_KEY to vacancyId)
                )
            }
            onFavoriteIconClickListener = { vacancyId ->
                viewModel.toggleVacancyFavoriteStatus(vacancyId)
            }
        }

    }

}