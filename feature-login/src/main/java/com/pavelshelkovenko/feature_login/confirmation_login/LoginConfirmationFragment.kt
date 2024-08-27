package com.pavelshelkovenko.feature_login.confirmation_login

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_login.R
import com.pavelshelkovenko.feature_login.databinding.FragmentLoginConfirmationBinding
import com.pavelshelkovenko.feature_login.openKeyboard
import com.pavelshelkovenko.navigation.DeepLinkNavCommand
import com.pavelshelkovenko.navigation.NavCommand
import com.pavelshelkovenko.navigation.navigate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginConfirmationFragment : Fragment(R.layout.fragment_login_confirmation) {

    private val binding: FragmentLoginConfirmationBinding by viewBinding(
        FragmentLoginConfirmationBinding::bind
    )
    private val viewModel: LoginConfirmationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupNavigation()
        observeScreenState()
    }

    private fun setupUi() {
        with(binding) {
            titleLoginConfirmation.text = resources.getString(
                com.pavelshelkovenko.ui.R.string.title_login_confirmation,
                arguments?.getString(EMAIL_KEY)
            )
            pinCode.openKeyboard()
            pinCode.addTextChangedListener { pin ->
                viewModel.pinChanged(pin.toString())
            }
        }
    }

    private fun setupNavigation() {
        binding.confirmButton.setOnClickListener {
            navigate(
                NavCommand(
                    target = DeepLinkNavCommand(
                        url = Uri.parse("effmobcontest://main"),
                        isSingleTop = true
                    )
                )
            )
        }
    }

    private fun observeScreenState() {
        viewModel.screenState
            .flowWithLifecycle(lifecycle)
            .onEach(::render)
            .launchIn(lifecycleScope)
    }

    private fun render(newScreenState: LoginConfirmationScreenState) {
        with(binding) {
            confirmButton.isEnabled = newScreenState.isLoginConfirmed
        }
    }

    companion object {
        const val EMAIL_KEY = "email_key"
    }
}