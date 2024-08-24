package com.pavelshelkovenko.feature_login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelshelkovenko.feature_login.databinding.RegistrationScreenFirstBinding

class LoginFragment: Fragment(R.layout.registration_screen_first) {

    private val binding: RegistrationScreenFirstBinding by viewBinding(RegistrationScreenFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}