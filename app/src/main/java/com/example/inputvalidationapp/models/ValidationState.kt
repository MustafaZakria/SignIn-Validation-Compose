package com.example.inputvalidationapp.models

data class ValidationState(
    val emailErrorState: String = "",
    val passwordErrorState: String = "",
    val confirmPasswordErrorState: String = "",
)
