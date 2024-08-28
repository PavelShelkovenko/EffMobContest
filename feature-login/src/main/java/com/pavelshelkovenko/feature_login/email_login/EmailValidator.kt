package com.pavelshelkovenko.feature_login.email_login

import android.util.Patterns

class EmailValidator {

    fun validate(newEmail: String): Boolean {
        return newEmail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()
    }
}