package com.pavelshelkovenko.feature_vacancy_details

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.domain.models.Vacancy
import com.pavelshelkovenko.feature_vacancy_details.BottomSheetRespondFragment.Companion.NO_ARGUMENT
import com.pavelshelkovenko.feature_vacancy_details.databinding.FragmentVacancyDetailsBinding
import com.pavelshelkovenko.navigation.NavCommand
import com.pavelshelkovenko.navigation.NavCommands
import com.pavelshelkovenko.navigation.navigate
import com.pavelshelkovenko.ui.delegate_adapter.BaseAdapter
import com.pavelshelkovenko.ui.extensions.gone
import com.pavelshelkovenko.ui.extensions.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class VacancyDetailsFragment : Fragment(R.layout.fragment_vacancy_details) {

    private val binding: FragmentVacancyDetailsBinding by viewBinding(FragmentVacancyDetailsBinding::bind)
    private val viewModel: VacancyDetailsViewModel by viewModel()

    private val questionAdapter by lazy {
        BaseAdapter.Builder()
            .add(QuestionAdapter())
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupClickListeners()
        observeScreenState()
    }

    private fun observeScreenState() {
        val vacancyId = arguments?.getString(VACANCY_ID_KEY)
        vacancyId?.let {
            viewModel.loadData(vacancyId = it)
        }
        viewModel.screenState
            .flowWithLifecycle(lifecycle)
            .onEach(::render)
            .launchIn(lifecycleScope)
    }

    private fun render(newScreenState: VacancyDetailsScreenState) {
        with(binding) {
            when (newScreenState) {
                VacancyDetailsScreenState.Initial -> {
                    mainContent.gone()
                    titleError.gone()
                    progressBar.gone()
                }

                VacancyDetailsScreenState.Loading -> {
                    mainContent.gone()
                    titleError.gone()
                    progressBar.visible()
                }

                is VacancyDetailsScreenState.Content -> {
                    mainContent.visible()
                    titleError.gone()
                    progressBar.gone()
                    bindMainContentData(newScreenState.vacancy)
                    questionAdapter.submitList(newScreenState.questions)
                }

                VacancyDetailsScreenState.Error -> {
                    titleError.visible()
                    mainContent.gone()
                    progressBar.gone()
                }
            }
        }
    }

    private fun setupClickListeners() {
        val questionAdapter = questionAdapter.delegates.get(0) as QuestionAdapter
        with(questionAdapter) {
            onQuestionClickListener = { question: String ->
                navigate(
                    NavCommand(
                        target = NavCommands.DeepLink(
                            url = Uri.parse("effmobcontest://bottom_sheet_respond/$question"),
                            isSingleTop = false
                        )
                    )
                )
            }
        }
        val vacancyId = arguments?.getString(VACANCY_ID_KEY)

        with(binding) {
            icBack.setOnClickListener {
                findNavController().popBackStack()
            }
            applyForVacancyButton.setOnClickListener {
                navigate(
                    NavCommand(
                        target = NavCommands.DeepLink(
                            url = Uri.parse("effmobcontest://bottom_sheet_respond/$NO_ARGUMENT"),
                            isSingleTop = false
                        ),
                    )
                )
            }
            icFavoriteSelected.setOnClickListener {
                vacancyId?.let {
                    viewModel.toggleVacancyFavoriteStatus(vacancyId = it)
                }
            }
            icFavoriteUnselected.setOnClickListener {
                vacancyId?.let {
                    viewModel.toggleVacancyFavoriteStatus(vacancyId = it)
                }
            }
        }

    }

    private fun setupAdapter() {
        binding.questionsRv.adapter = questionAdapter
    }

    private fun bindMainContentData(vacancy: Vacancy) {
        with(binding) {
            vacancyTitle.text = vacancy.title
            salaryFull.text = vacancy.salary.full
            requiredExperience.text =
                resources.getString(R.string.required_experience, vacancy.experience.text)
            employmentType.text = vacancy.schedules
            if (vacancy.appliedNumber == 0) {
                respondsCard.root.gone()
            } else {
                respondsCard.root.visible()
                respondsCard.cardIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        com.pavelshelkovenko.ui.R.drawable.ic_respond,
                        context?.theme
                    )
                )
                respondsCard.cardTitle.text = resources.getQuantityString(
                    com.pavelshelkovenko.ui.R.plurals.applied_number,
                    vacancy.appliedNumber,
                    vacancy.appliedNumber,
                )
            }
            if (vacancy.lookingNumber == 0) {
                watchingCard.root.gone()
            } else {
                watchingCard.root.visible()
                watchingCard.cardIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        com.pavelshelkovenko.ui.R.drawable.ic_watching,
                        context?.theme
                    )
                )
                watchingCard.cardTitle.text = resources.getQuantityString(
                    com.pavelshelkovenko.ui.R.plurals.looking_number_details,
                    vacancy.lookingNumber,
                    vacancy.lookingNumber,
                )
            }
            locationCard.companyTitle.text = vacancy.company
            locationCard.companyAddress.text = resources.getString(
                com.pavelshelkovenko.ui.R.string.company_address_pattern,
                vacancy.address.town,
                vacancy.address.street,
                vacancy.address.house,
            )
            companyDescription.text = vacancy.description
            responsibilitiesDescription.text = vacancy.responsibilities
            if (vacancy.isFavorite) {
                icFavoriteSelected.visible()
                icFavoriteUnselected.gone()
            } else {
                icFavoriteSelected.gone()
                icFavoriteUnselected.visible()
            }
        }
    }


    companion object {
        const val VACANCY_ID_KEY: String = "vacancy_id"
    }
}