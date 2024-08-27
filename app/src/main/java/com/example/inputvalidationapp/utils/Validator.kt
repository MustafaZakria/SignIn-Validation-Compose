package com.example.inputvalidationapp.utils

import android.util.Patterns
import com.example.inputvalidationapp.models.EmailValidationType
import com.example.inputvalidationapp.models.PasswordValidationType

object Validator {

    fun validateEmail(email: String): EmailValidationType {
        return if (email.isBlank())
            EmailValidationType.EmptyEmail
        else if (listOf("", "").contains(email))
            EmailValidationType.ExistEmail
        else if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
            EmailValidationType.ValidEmail
        else EmailValidationType.InvalidEmail

    }

    fun validatePassword(password: String): PasswordValidationType {
        return if (password.isBlank())
            PasswordValidationType.EmptyPassword
        else if (password.length < 8 || password.count(Char::isUpperCase) == 0 || !password.contains(
                "[0-9]".toRegex()
            )
        )
            PasswordValidationType.InvalidPassword
        else PasswordValidationType.ValidPassword
    }

    fun validateMatchingPasswords(password1: String, password2: String): PasswordValidationType {
        return if (password1 == password2)
            PasswordValidationType.ValidPassword
        else PasswordValidationType.UnmatchedPassword
    }


}