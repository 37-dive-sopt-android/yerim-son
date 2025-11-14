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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.component.DiveBasicButton
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.DiveValidator
import com.sopt.dive.util.UiState
import com.sopt.dive.util.UserPreferences

@Composable
fun LoginRoute(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is UiState.Success -> {
            val data = (uiState as UiState.Success<LoginUiState>).data

            LaunchedEffect(data.userId) {
                if (data.userId != null) {
                    Toast.makeText(context, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                    onLoginSuccess()
                    viewModel.resetLoginState()
                }
            }

            LoginScreen(
                id = data.username,
                pw = data.password,
                onIdChange = viewModel::updateUsername,
                onPasswordChange = viewModel::updatePassword,
                onTextClick = onSignUpClick,
                onButtonClick = viewModel::login
            )
        }
        is UiState.Failure -> {
            LaunchedEffect(Unit) {
                Toast.makeText(context, "아이디 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                viewModel.resetLoginState()
            }
        }
        else -> {}
    }
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