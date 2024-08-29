package com.pavelshelkovenko.feature_favorite_vacancies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_favorite_vacancies.databinding.FragmentFavoriteVacanciesBinding
import com.pavelshelkovenko.feature_search_vacancies.common.VacancyAdapter
import com.pavelshelkovenko.ui.delegate_adapter.BaseAdapter
import com.pavelshelkovenko.ui.extensions.gone
import com.pavelshelkovenko.ui.extensions.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.pavelshelkovenko.feature_vacancy_details.VacancyDetailsFragment.Companion.VACANCY_ID_KEY

class FavoriteVacanciesFragment: Fragment(R.layout.fragment_favorite_vacancies) {

    private val binding: FragmentFavoriteVacanciesBinding by viewBinding(FragmentFavoriteVacanciesBinding::bind)
    private val viewModel: FavoriteVacanciesViewModel by viewModel()

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

    private fun observeScreenState() {
        viewModel.loadData()
        viewModel.screenState
            .flowWithLifecycle(lifecycle)
            .onEach(::render)
            .launchIn(lifecycleScope)
    }

    private fun render(newScreenState: FavoriteVacanciesScreenState) {
        with(binding) {
            when (newScreenState) {
                FavoriteVacanciesScreenState.Initial -> {
                    progressBar.gone()
                    vacanciesRv.gone()
                }
                FavoriteVacanciesScreenState.Loading -> {
                    progressBar.visible()
                    vacanciesRv.gone()
                }
                is FavoriteVacanciesScreenState.Content -> {
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

    private fun setupUi() {
        with(binding) {
            vacanciesRv.adapter = vacancyAdapter
        }
    }

    private fun setupClickListeners() {
        val adapter = vacancyAdapter.delegates.get(0) as VacancyAdapter

        with(adapter) {
            onVacancyClickListener = { vacancyId ->
                findNavController().navigate(
                    R.id.action_favoriteVacanciesFragment_to_vacancyDetails,
                    bundleOf(VACANCY_ID_KEY to vacancyId)
                )
            }
            onFavoriteIconClickListener = { vacancyId ->
                viewModel.toggleVacancyFavoriteStatus(vacancyId)
            }
        }
    }
}