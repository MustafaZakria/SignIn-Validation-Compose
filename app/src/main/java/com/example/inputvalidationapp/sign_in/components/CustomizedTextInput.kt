package com.example.inputvalidationapp.sign_in.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomizedTextInput(
    value: String,
    onValueChange: (value: String) -> Unit,
    error: String,
    hint: String,
    keyboardType: KeyboardType,
    passwordVisible: Boolean = true,
    onIconClick: () -> Unit
) {
    TextField(
        value = value,
        isError = error.isNotEmpty(),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = {
            if (value.isBlank()) Text(hint)
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .testTag(hint),
        supportingText = {
            if (error.isNotBlank()) {
                Text(
                    error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.testTag(error)
                )
            }
        },
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = onIconClick) {
                    Icon(imageVector = image, description)
                }
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
    )
}