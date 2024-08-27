package com.pavelshelkovenko.feature_login.email_login

import androidx.lifecycle.ViewModel
import com.pavelshelkovenko.feature_login.isValidEmail
import kotlinx.coroutines.flow.MutableStateFlow

class EmailLoginViewModel: ViewModel() {

    var screenState = MutableStateFlow(EmailLoginScreenState())
        private set


    fun isValidEmail(emailForValidation: String): Boolean {
        screenState.value = screenState.value.copy(
            isError = false
        )
        return emailForValidation.isValidEmail()
    }

    fun setEmailError() {
        screenState.value = screenState.value.copy(
            isError = true
        )
    }
}