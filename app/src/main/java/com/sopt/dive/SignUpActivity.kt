package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    Text(
        text = "Sign Up Screen",
        modifier = Modifier,
        fontSize = 16.sp
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen()
    }
}