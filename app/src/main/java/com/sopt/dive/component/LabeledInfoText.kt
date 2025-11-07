package com.sopt.dive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun LabeledInfoText(
    label: String,
    value: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(bottom = 32.dp)) {
        Text(
            text = label,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = value ?: "",
            fontSize = 18.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LabeledInfoTextPreview() {
    DiveTheme {
        val id = "testUser"

        Column(modifier = Modifier.fillMaxWidth()) {
            LabeledInfoText(
                label = "ID",
                value = id
            )
        }
    }
}