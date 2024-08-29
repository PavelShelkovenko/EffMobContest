package com.pavelshelkovenko.feature_login.di

import com.pavelshelkovenko.feature_login.confirmation_login.LoginConfirmationViewModel
import com.pavelshelkovenko.feature_login.email_login.EmailLoginViewModel
import com.pavelshelkovenko.feature_login.email_login.EmailValidator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { EmailLoginViewModel(emailValidator = get()) }
    viewModel { LoginConfirmationViewModel() }
    single<EmailValidator> { EmailValidator() }
}