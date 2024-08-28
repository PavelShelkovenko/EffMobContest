package com.pavelshelkovenko.feature_login.email_login

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_login.R
import com.pavelshelkovenko.feature_login.confirmation_login.LoginConfirmationFragment
import com.pavelshelkovenko.feature_login.databinding.FragmentEmailLoginBinding
import com.pavelshelkovenko.ui.extensions.gone
import com.pavelshelkovenko.ui.extensions.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class EmailLoginFragment : Fragment(R.layout.fragment_email_login) {

    private val binding: FragmentEmailLoginBinding by viewBinding(FragmentEmailLoginBinding::bind)
    private val viewModel: EmailLoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeScreenState()
    }

    private fun setupUi() {
        with(binding.employeeCard) {
            emailEditText.addTextChangedListener { text ->
                if (text?.isNotEmpty() == true) {
                    continueButton.isEnabled = true
                    mailIcon.gone()
                    clearIcon.visible()
                } else {
                    continueButton.isEnabled = false
                    mailIcon.visible()
                    clearIcon.gone()
                }
                viewModel.isValidEmail(text.toString())
            }
            clearIcon.setOnClickListener {
                emailEditText.text.clear()
            }
            continueButton.setOnClickListener {
                if (viewModel.isValidEmail(emailEditText.text.toString())) {
                    findNavController().navigate(
                        R.id.action_loginFragment_to_loginConfirmationFragment,
                        bundleOf(LoginConfirmationFragment.EMAIL_KEY to emailEditText.text.toString().trim())
                    )
                } else {
                    viewModel.setEmailError()
                }
            }
        }
    }

    private fun observeScreenState() {
        viewModel.screenState
            .flowWithLifecycle(lifecycle)
            .onEach(::render)
            .launchIn(lifecycleScope)
    }

    private fun render(newScreenState: EmailLoginScreenState) {
        with(binding.employeeCard) {
            if (newScreenState.isError) {
                errorMessage.visible()
                inputField.foreground = ResourcesCompat.getDrawable(
                    resources,
                    com.pavelshelkovenko.ui.R.drawable.error_border,
                    context?.theme
                )
            } else {
                errorMessage.gone()
                inputField.foreground = null
            }
        }
    }
}
