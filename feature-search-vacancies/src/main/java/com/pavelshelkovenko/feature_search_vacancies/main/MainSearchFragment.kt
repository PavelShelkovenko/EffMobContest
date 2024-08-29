package com.pavelshelkovenko.feature_search_vacancies.main

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
import com.pavelshelkovenko.feature_search_vacancies.databinding.FragmentMainSearchBinding
import com.pavelshelkovenko.feature_vacancy_details.VacancyDetailsFragment.Companion.VACANCY_ID_KEY
import com.pavelshelkovenko.navigation.NavCommand
import com.pavelshelkovenko.navigation.NavCommands
import com.pavelshelkovenko.navigation.navigate
import com.pavelshelkovenko.ui.delegate_adapter.BaseAdapter
import com.pavelshelkovenko.ui.extensions.gone
import com.pavelshelkovenko.ui.extensions.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainSearchFragment: Fragment(R.layout.fragment_main_search) {

    private val binding: FragmentMainSearchBinding by viewBinding(FragmentMainSearchBinding::bind)
    private val viewModel: MainSearchViewModel by viewModel()

    private val vacancyAdapter by lazy {
        BaseAdapter.Builder()
            .add(VacancyAdapter())
            .build()
    }

    private val offerAdapter by lazy {
        BaseAdapter.Builder()
            .add(OfferAdapter())
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        setupClickListeners()
        observeScreenState()
    }

    private fun setupAdapters() {
        with(binding) {
            offersRv.adapter = offerAdapter
            vacanciesRv.adapter = vacancyAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.syncVacanciesWithCache()
    }

    private fun observeScreenState() {
        viewModel.screenState
            .flowWithLifecycle(lifecycle)
            .onEach(::render)
            .launchIn(lifecycleScope)
    }

    private fun render(newScreenState: MainSearchScreenState) {
        with(binding) {
            when(newScreenState) {
                MainSearchScreenState.Initial -> {
                    progressBar.gone()
                    mainContent.gone()
                    extendedSearchButton.gone()
                    errorContent.gone()
                }
                MainSearchScreenState.Loading -> {
                    progressBar.visible()
                    mainContent.gone()
                    extendedSearchButton.gone()
                    errorContent.gone()
                }
                is MainSearchScreenState.Content -> {
                    progressBar.gone()
                    errorContent.gone()
                    mainContent.visible()
                    extendedSearchButton.visible()
                    vacancyAdapter.submitList(newScreenState.vacancies)
                    offerAdapter.submitList(newScreenState.offers)
                    extendedSearchButton.text = resources.getQuantityString(
                        com.pavelshelkovenko.ui.R.plurals.vacancies_number,
                        newScreenState.vacancies.size + VACANCY_COUNT_TO_SHOW,
                        newScreenState.vacancies.size + VACANCY_COUNT_TO_SHOW,
                    )
                }
                is MainSearchScreenState.Error -> {
                    errorContent.visible()
                    progressBar.gone()
                    mainContent.gone()
                    extendedSearchButton.gone()
                }
            }
        }
    }

    private fun setupClickListeners() {
        val vacancyAdapter = vacancyAdapter.delegates.get(0) as VacancyAdapter
        val offerAdapter = offerAdapter.delegates.get(0) as OfferAdapter

        with(vacancyAdapter) {
            onVacancyClickListener = { vacancyId ->
                findNavController().navigate(
                    R.id.action_mainSearchFragment_to_vacancyDetails,
                    bundleOf(VACANCY_ID_KEY to vacancyId)
                )
            }
            onFavoriteIconClickListener = { vacancyId ->
                viewModel.toggleVacancyFavoriteStatus(vacancyId)
            }
        }

        with(offerAdapter) {
            onOfferClickListener = { link ->
                navigate(
                    NavCommand(
                        target = NavCommands.Browser(url = link)
                    )
                )
            }
        }

        with(binding) {
            repeatButton.setOnClickListener {
                viewModel.loadData()
            }
            extendedSearchButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_mainSearchFragment_to_extendedSearchFragment,
                )
            }
        }
    }

    companion object {
        const val VACANCY_COUNT_TO_SHOW = 3
    }
}

