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

    val userPrefs = remember { UserPreferences(context) }

    SignUpScreen(
        id = id,
        pw = pw,
        nickname = nickname,
        mbti = mbti,
        onIdChange = setId,
        onPasswordChange = setPw,
        onNicknameChange = setNickname,
        onMbtiChange = setMbti,
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
    nickname: String,
    mbti: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onMbtiChange: (String) -> Unit,
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
                .padding(bottom = 120.dp)
                .padding(horizontal = 30.dp),
        )

        LabeledTextField(
            label = "ID",
            value = id,
            onValueChange = onIdChange,
            placeholder = "아이디를 입력해주세요"
        )

        LabeledTextField(
            label = "비밀번호",
            value = pw,
            onValueChange = onPasswordChange,
            placeholder = "비밀번호를 입력해주세요",
            isPassword = true
        )

        LabeledTextField(
            label = "닉네임",
            value = nickname,
            onValueChange = onNicknameChange,
            placeholder = "닉네임을 입력해주세요"
        )

        LabeledTextField(
            label = "MBTI",
            value = mbti,
            onValueChange = onMbtiChange,
            placeholder = "MBTI를 입력해주세요"
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
            nickname = "테스트",
            mbti = "ENFJ",
            onIdChange = {},
            onPasswordChange = {},
            onNicknameChange = {},
            onMbtiChange = {},
            onButtonClick = {}
        )
    }
}
