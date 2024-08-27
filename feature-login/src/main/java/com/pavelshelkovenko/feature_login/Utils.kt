package com.pavelshelkovenko.feature_login

import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun String.isValidEmail() = isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun EditText.openKeyboard() {
    if (this.requestFocus()) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
