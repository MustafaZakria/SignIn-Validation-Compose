package com.example.inputvalidationapp.sign_in

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inputvalidationapp.R
import com.example.inputvalidationapp.models.ValidationState
import com.example.inputvalidationapp.sign_in.components.AuthFieldsColumn
import com.example.inputvalidationapp.sign_in.components.CustomizedButton
import com.example.inputvalidationapp.sign_in.components.CustomizedTextInput
import com.example.inputvalidationapp.sign_in.components.SnackBar
import com.example.inputvalidationapp.ui.theme.InputValidationAppTheme
import com.example.inputvalidationapp.utils.Constants.SUCCESSFUL_SIGN_IN
import com.example.modified_snackbar.presentation.ComposeModifiedSnackbar
import com.example.modified_snackbar.presentation.ComposeModifiedSnackbarSuccess
import com.example.modified_snackbar.presentation.rememberComposeModifiedSnackbarState
import com.example.modified_snackbar.util.ComposeModifiedSnackbarPosition
import com.example.modified_snackbar.util.ComposeModifierSnackbarDuration

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InputValidationAppTheme {
                ValidationScreen(viewModel)
            }
        }
    }

}

@Composable
fun ValidationScreen(viewModel: MainViewModel) {
    val emailState = viewModel.emailState
    val passwordState = viewModel.passwordState
    val confirmPasswordState = viewModel.confirmPasswordState
    val validationState = viewModel.validationState
    val navigateState = viewModel.navigateToSuccessScreen
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val confirmPasswordVisible = rememberSaveable { mutableStateOf(false) }

    val snackBarState = rememberComposeModifiedSnackbarState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                AuthFieldsColumn(
                    emailState.value,
                    passwordState.value,
                    confirmPasswordState.value,
                    validationState,
                    onChangeEmail = { value -> viewModel.onChangeEmail(value) },
                    onChangePassword = { value -> viewModel.onChangePassword(value) },
                    onChangeConfirmPassword = { value -> viewModel.onChangePasswordConfirm(value) },
                    passwordVisible = passwordVisible.value,
                    confirmPasswordVisible = confirmPasswordVisible.value,
                    onPasswordIconClick = { passwordVisible.value = !(passwordVisible.value) },
                    onConfirmPassIconClick = {
                        confirmPasswordVisible.value = !(confirmPasswordVisible.value)
                    }
                )
                CustomizedButton(stringResource(id = R.string.sign_in)) {
                    viewModel.validateAndNavigate()
                    if (navigateState.value) {
                        snackBarState.showSnackbar(SUCCESSFUL_SIGN_IN)
                    }
                }
            }
        }
//        Box(contentAlignment = Alignment.BottomCenter) {
//            if (navigateState.value) {
//                SnackBar(stringResource(id = R.string.successful_sign_in))
//            }
//        }
        ComposeModifiedSnackbarSuccess(
            state = snackBarState,
            position = ComposeModifiedSnackbarPosition.Bottom,
            duration = ComposeModifierSnackbarDuration.SHORT,
            withDismissAction = true
        )

    }
}

@Preview(showBackground = true)
@Composable
fun InputValidationPreview() {
    InputValidationAppTheme {
        ValidationScreen(viewModel = MainViewModel())
    }
}
