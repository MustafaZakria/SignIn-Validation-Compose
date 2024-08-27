package com.example.inputvalidationapp.models

import com.example.inputvalidationapp.utils.Constants.ALREADY_EXIST_EMAIL
import com.example.inputvalidationapp.utils.Constants.EMPTY_EMAIL
import com.example.inputvalidationapp.utils.Constants.EMPTY_STRING
import com.example.inputvalidationapp.utils.Constants.INVALID_EMAIL


sealed class EmailValidationType(val msg: String, val isValid: Boolean) {
    data object InvalidEmail: EmailValidationType(INVALID_EMAIL, false)
    data object ExistEmail: EmailValidationType(ALREADY_EXIST_EMAIL, false)
    data object EmptyEmail: EmailValidationType(EMPTY_EMAIL, false)
    data object ValidEmail: EmailValidationType(EMPTY_STRING, true)
}