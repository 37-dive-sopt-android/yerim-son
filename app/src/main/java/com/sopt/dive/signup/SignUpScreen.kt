package com.sopt.dive.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.component.DiveBasicButton
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.DiveValidator
import com.sopt.dive.util.UserPreferences

@Composable
fun SignUpRoute(
    onSignUpSuccess: () -> Unit,
) {
    val context = LocalContext.current

    val (id, setId) = remember { mutableStateOf("") }
    val (pw, setPw) = remember { mutableStateOf("") }
    val (nickname, setNickname) = remember { mutableStateOf("") }
    val (mbti, setMbti) = remember { mutableStateOf("") }
    val (age, setAge) = remember { mutableStateOf("") }

    val userPrefs = remember { UserPreferences(context) }

    SignUpScreen(
        id = id,
        pw = pw,
        name = nickname,
        email = mbti,
        age = age,
        onIdChange = setId,
        onPasswordChange = setPw,
        onNameChange = setNickname,
        onEmailChange = setMbti,
        onAgeChange = setAge,
        onButtonClick = {
            val result = DiveValidator.validateSignUp(id, pw, nickname, mbti)
            if (!result.isValid) {
                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
            } else {
                userPrefs.registerUser(id, pw, nickname, mbti)
                Toast.makeText(context, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                onSignUpSuccess()
            }
        }
    )
}

@Composable
fun SignUpScreen(
    id: String,
    pw: String,
    name: String,
    email: String,
    age: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    ) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SIGN UP",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(bottom = 80.dp)
                .padding(horizontal = 30.dp),
        )

        LabeledTextField(
            label = "ID",
            value = id,
            onValueChange = onIdChange,
            placeholder = "사용자명을 입력해주세요"
        )

        LabeledTextField(
            label = "비밀번호",
            value = pw,
            onValueChange = onPasswordChange,
            placeholder = "비밀번호를 입력해주세요",
            isPassword = true
        )

        LabeledTextField(
            label = "이름",
            value = name,
            onValueChange = onNameChange,
            placeholder = "닉네임을 입력해주세요"
        )

        LabeledTextField(
            label = "이메일",
            value = email,
            onValueChange = onEmailChange,
            placeholder = "이메일을 입력해주세요"
        )

        LabeledTextField(
            label = "나이",
            value = age,
            onValueChange = onAgeChange,
            placeholder = "나이를 입력해주세요"
        )

        Spacer(modifier = Modifier.weight(1f))

        DiveBasicButton(
            text = "회원가입 하기",
            onClick = onButtonClick,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(
            id = "testUser",
            pw = "123456",
            name = "테스트",
            email = "ENFJ",
            age = "1",
            onIdChange = {},
            onPasswordChange = {},
            onNameChange = {},
            onEmailChange = {},
            onAgeChange = {},
            onButtonClick = {},
        )
    }
}
