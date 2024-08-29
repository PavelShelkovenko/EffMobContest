package com.pavelshelkovenko.feature_login.confirmation_login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginConfirmationViewModel: ViewModel() {

    var screenState = MutableStateFlow(LoginConfirmationScreenState())
        private set

    fun pinChanged(pin: String) {
        if (pin.length == PIN_LENGTH_FOR_CONFIRMATION) {
            screenState.value = screenState.value.copy(
                isLoginConfirmed = true
            )
        } else {
            screenState.value = screenState.value.copy(
                isLoginConfirmed = false
            )
        }
    }

    companion object {
        private const val PIN_LENGTH_FOR_CONFIRMATION = 4
    }
}