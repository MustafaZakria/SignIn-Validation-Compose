package com.example.inputvalidationapp.models

import com.example.inputvalidationapp.utils.Constants.EMPTY_PASSWORD
import com.example.inputvalidationapp.utils.Constants.EMPTY_STRING
import com.example.inputvalidationapp.utils.Constants.INVALID_PASSWORD
import com.example.inputvalidationapp.utils.Constants.INCORRECT_PASSWORD
import com.example.inputvalidationapp.utils.Constants.UNMATCHED_PASSWORDS

sealed class PasswordValidationType(val msg: String, val isValid: Boolean) {
    data object InvalidPassword: PasswordValidationType(INVALID_PASSWORD, false)
    data object UnmatchedPassword: PasswordValidationType(UNMATCHED_PASSWORDS, false)
    data object UnCorrectPassword: PasswordValidationType(INCORRECT_PASSWORD, false)
    data object EmptyPassword: PasswordValidationType(EMPTY_PASSWORD, false)
    data object ValidPassword: PasswordValidationType(EMPTY_STRING, true)

}