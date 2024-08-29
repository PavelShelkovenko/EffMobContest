package com.pavelshelkovenko.feature_vacancy_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pavelshelkovenko.feature_vacancy_details.databinding.BottomSheetRespondBinding
import com.pavelshelkovenko.ui.extensions.gone
import com.pavelshelkovenko.ui.extensions.visible

class BottomSheetRespondFragment : BottomSheetDialogFragment(R.layout.bottom_sheet_respond) {

    private val binding: BottomSheetRespondBinding by viewBinding(BottomSheetRespondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        Glide.with(this).load(com.pavelshelkovenko.ui.R.drawable.ic_mock_avatar).circleCrop()
            .into(binding.icAvatar)
        with(binding) {
            val question = arguments?.getString(QUESTION_KEY)
            if (question != NO_ARGUMENT) {
                addRespondButton.gone()
                inputField.apply {
                    visible()
                    setText(
                        resources.getString(
                            com.pavelshelkovenko.ui.R.string.question_pattern,
                            question
                        )
                    )
                }
            } else {
                addRespondButton.visible()
                inputField.gone()
            }
            addRespondButton.setOnClickListener {
                addRespondButton.gone()
                inputField.visible()
            }
            applyForVacancyButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    companion object {
        const val QUESTION_KEY = "question"
        const val NO_ARGUMENT = "noargument"
    }
}