package com.example.inputvalidationapp.sign_in.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.inputvalidationapp.R
import com.example.inputvalidationapp.models.ValidationState

@Composable
fun AuthFieldsColumn(
    emailStateValue: String,
    passwordStateValue: String,
    confirmPasswordStateValue: String,
    validationState: State<ValidationState>,
    onChangeEmail: (value: String) -> Unit,
    onChangePassword: (value: String) -> Unit,
    onChangeConfirmPassword: (value: String) -> Unit,
    passwordVisible: Boolean,
    confirmPasswordVisible: Boolean,
    onPasswordIconClick: () -> Unit,
    onConfirmPassIconClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 10.dp, bottom = 10.dp)
    ) {
        CustomizedTextInput(
            value = emailStateValue,
            onValueChange = onChangeEmail,
            error = validationState.value.emailErrorState,
            hint = stringResource(id = R.string.email_hint),
            keyboardType = KeyboardType.Text,
            onIconClick = {}
        )
        CustomizedTextInput(
            value = passwordStateValue,
            onValueChange = onChangePassword,
            error = validationState.value.passwordErrorState,
            hint = stringResource(id = R.string.password_hint),
            keyboardType = KeyboardType.Password,
            passwordVisible = passwordVisible,
            onIconClick = onPasswordIconClick
        )
        CustomizedTextInput(
            value = confirmPasswordStateValue,
            onValueChange = onChangeConfirmPassword,
            error = validationState.value.confirmPasswordErrorState,
            hint = stringResource(id = R.string.confirm_password_hint),
            keyboardType = KeyboardType.Password,
            passwordVisible = confirmPasswordVisible,
            onIconClick = onConfirmPassIconClick
        )
    }
}