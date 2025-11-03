package com.sopt.dive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    Text(
        text = label,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        placeholder = { Text(placeholder) },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None
    )
}

@Preview(showBackground = true)
@Composable
private fun LabeledTextFieldPreview() {
    DiveTheme {
        val (text, setText) = remember { mutableStateOf("abcdef") }

        Column(modifier = Modifier.fillMaxWidth()) {
            LabeledTextField(
                label = "ID",
                value = text,
                onValueChange = { setText(it) },
                placeholder = "사용자 이름 입력",
            )
        }
    }
}