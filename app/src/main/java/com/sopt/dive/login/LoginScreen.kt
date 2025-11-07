package com.sopt.dive.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.component.DiveBasicButton
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.DiveValidator
import com.sopt.dive.util.UserPreferences

@Composable
fun LoginRoute(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    LoginScreen(
        id = id,
        pw = pw,
        onIdChange = { id = it },
        onPasswordChange = { pw = it },
        onTextClick = onSignUpClick,
        onButtonClick = {
            val validationResult = DiveValidator.validateLogin(id, pw)
            if (!validationResult.isValid) {
                Toast.makeText(context, validationResult.message, Toast.LENGTH_SHORT).show()
                return@LoginScreen
            }

            val isValidUser = userPrefs.isUserValid(id, pw)
            if (isValidUser) {
                Toast.makeText(context, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                onLoginSuccess()
            } else {
                Toast.makeText(context, "아이디 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@Composable
fun LoginScreen(
    id: String,
    pw: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTextClick: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome To Sopt",
            fontSize = 28.sp,
            modifier = modifier
                .padding(top = 20.dp, bottom = 240.dp)
                .padding(horizontal = 30.dp),
        )

        LabeledTextField(
            label = "ID",
            value = id,
            onValueChange = onIdChange,
            placeholder = "사용자 이름 입력"
        )

        LabeledTextField(
            label = "비밀번호",
            value = pw,
            onValueChange = onPasswordChange,
            placeholder = "비밀번호 입력",
            isPassword = true
        )

        Spacer(modifier = modifier.weight(1f))

        Text(
            text = "회원가입하기",
            textDecoration = TextDecoration.Underline,
            modifier = modifier.clickable { onTextClick() }
        )
        DiveBasicButton(
            text = "로그인하기",
            onClick = onButtonClick,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    DiveTheme {
        LoginScreen(
            id = "testUser",
            pw = "1234",
            onIdChange = {},
            onPasswordChange = {},
            onTextClick = {},
            onButtonClick = {}
        )
    }
}