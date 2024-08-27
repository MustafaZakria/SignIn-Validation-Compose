package com.example.inputvalidationapp.sign_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.inputvalidationapp.utils.Validator.validateEmail
import com.example.inputvalidationapp.utils.Validator.validateMatchingPasswords
import com.example.inputvalidationapp.utils.Validator.validatePassword
import com.example.inputvalidationapp.models.ValidationState

class MainViewModel : ViewModel() {
    private val _emailState = mutableStateOf("")
    val emailState: State<String> = _emailState

    private val _passwordState = mutableStateOf("")
    val passwordState: State<String> = _passwordState

    private val _confirmPasswordState = mutableStateOf("")
    val confirmPasswordState: State<String> = _confirmPasswordState

    private val _validationState = mutableStateOf(ValidationState())
    val validationState: State<ValidationState> = _validationState


    fun onChangeEmail(newValue: String) {
        _emailState.value = newValue
        _validationState.value =
            _validationState.value.copy(emailErrorState = validateEmail(newValue).msg)
    }

    fun onChangePassword(newValue: String) {
        _passwordState.value = newValue
        _validationState.value =
            _validationState.value.copy(passwordErrorState = validatePassword(newValue).msg)

    }

    fun onChangePasswordConfirm(newValue: String) {
        _confirmPasswordState.value = newValue
        _validationState.value = _validationState.value.copy(
            confirmPasswordErrorState = validateMatchingPasswords(
                newValue,
                _passwordState.value
            ).msg
        )
    }

    private val _navigateToSuccessScreen = mutableStateOf(false)
    val navigateToSuccessScreen: State<Boolean> = _navigateToSuccessScreen

    fun validateAndNavigate() {
        val email = _emailState.value
        val password = _passwordState.value
        val passwordConfirm = _confirmPasswordState.value

        _navigateToSuccessScreen.value =
            validateEmail(email).isValid &&
                    validatePassword(password).isValid &&
                    validateMatchingPasswords(password, passwordConfirm).isValid
    }

}



