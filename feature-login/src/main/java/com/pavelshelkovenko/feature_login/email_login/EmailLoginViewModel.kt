package com.pavelshelkovenko.feature_login.email_login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class EmailLoginViewModel(
    private val emailValidator: EmailValidator
): ViewModel() {

    var screenState = MutableStateFlow(EmailLoginScreenState())
        private set

    fun isValidEmail(emailForValidation: String): Boolean {
        screenState.value = screenState.value.copy(
            isError = false
        )
        return emailValidator.validate(emailForValidation)
    }

    fun setEmailError() {
        screenState.value = screenState.value.copy(
            isError = true
        )
    }
}