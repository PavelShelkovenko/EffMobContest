package com.pavelshelkovenko.feature_login

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.core.navigation.DeepLinkNavCommand
import com.pavelshelkovenko.core.navigation.NavCommand
import com.pavelshelkovenko.core.navigation.navigate
import com.pavelshelkovenko.feature_login.databinding.FragmentLoginConfirmationBinding

class LoginConfirmationFragment: Fragment(R.layout.fragment_login_confirmation) {

    private val binding: FragmentLoginConfirmationBinding by viewBinding(FragmentLoginConfirmationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}