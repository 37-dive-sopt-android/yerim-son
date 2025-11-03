package com.sopt.dive.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.component.DiveBasicButton
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.DiveValidator

@Composable
fun SignUpScreen(onSignUpSuccess: (String, String, String, String) -> Unit) {
    val context = LocalContext.current

    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

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
            onValueChange = { id = it },
            placeholder = "아이디를 입력해주세요"
        )

        LabeledTextField(
            label = "비밀번호",
            value = pw,
            onValueChange = { pw = it },
            placeholder = "비밀번호를 입력해주세요",
            isPassword = true
        )

        LabeledTextField(
            label = "닉네임",
            value = nickname,
            onValueChange = { nickname = it },
            placeholder = "닉네임을 입력해주세요"
        )

        LabeledTextField(
            label = "MBTI",
            value = mbti,
            onValueChange = { mbti = it },
            placeholder = "MBTI를 입력해주세요"
        )

        Spacer(modifier = Modifier.weight(1f))

        DiveBasicButton(
            text = "회원가입 하기",
            onClick = {
                val result = DiveValidator.validateSignUp(id, pw, nickname, mbti)
                if (!result.isValid) {
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                } else {
                    onSignUpSuccess(id, pw, nickname, mbti)
                }
            },
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen { _, _, _, _ -> }
    }
}
