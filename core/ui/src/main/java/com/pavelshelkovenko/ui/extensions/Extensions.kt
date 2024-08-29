package com.pavelshelkovenko.ui.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.openKeyboard() {
    if (this.requestFocus()) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
