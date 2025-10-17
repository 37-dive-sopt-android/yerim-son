package com.sopt.dive

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.sopt.dive.ui.theme.DiveTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                SignUpScreen { id, pw, nickname, mbti ->
                    val resultIntent = Intent().apply {
                        putExtra("id", id)
                        putExtra("pw", pw)
                        putExtra("nickname", nickname)
                        putExtra("mbti", mbti)
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }
}

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

        Text(
            text = "ID",
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.Start)

        )
        TextField(
            value = id,
            onValueChange = { id = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            placeholder = { Text("아이디를 입력해주세요") },
            singleLine = true
        )

        Text(
            text = "비밀번호",
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.Start)
        )
        TextField(
            value = pw,
            onValueChange = { pw = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            placeholder = { Text("비밀번호를 입력해주세요") },
            singleLine = true
        )

        Text(
            text = "닉네임",
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.Start)
        )
        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            placeholder = { Text("닉네임을 입력해주세요") },
            singleLine = true
        )

        Text(
            text = "MBTI",
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.Start)
        )
        TextField(
            value = mbti,
            onValueChange = { mbti = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            placeholder = { Text("MBTI를 입력해주세요") },
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                when {
                    id.isBlank() || pw.isBlank() || nickname.isBlank() || mbti.isBlank() ->
                        Toast.makeText(context, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()

                    id.length !in 6..10 ->
                        Toast.makeText(context, "ID는 6~10자 사이여야 합니다.", Toast.LENGTH_SHORT).show()

                    pw.length !in 8..12 ->
                        Toast.makeText(context, "비밀번호는 8~12자 사이여야 합니다.", Toast.LENGTH_SHORT).show()

                    nickname.trim().isEmpty() ->
                        Toast.makeText(context, "닉네임은 공백만으로 구성될 수 없습니다.", Toast.LENGTH_SHORT).show()

                    else -> {
                        onSignUpSuccess(id, pw, nickname, mbti)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "회원가입 하기",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen { _, _, _, _ -> }
    }
}
