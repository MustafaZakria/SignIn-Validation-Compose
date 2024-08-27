package com.example.inputvalidationapp.sign_in.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnackBar(text: String) {
    Snackbar(
        Modifier.padding(24.dp),
        content = {
            Text(
                text = text,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize()
                    .wrapContentSize()
            )
        },
    )
}