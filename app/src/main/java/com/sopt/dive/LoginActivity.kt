package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    val context = LocalContext.current

    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    // 회원가입 결과로 받은 정보 저장
    var registeredId by remember { mutableStateOf("") }
    var registeredPw by remember { mutableStateOf("") }
    var registeredNickname by remember { mutableStateOf("") }
    var registeredMbti by remember { mutableStateOf("") }

    // registerForActivityResult로 회원가입 결과 받기
    val signUpLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == -1) {
                val data = result.data
                data?.let {
                    registeredId = it.getStringExtra("id") ?: ""
                    registeredPw = it.getStringExtra("pw") ?: ""
                    registeredNickname = it.getStringExtra("nickname") ?: ""
                    registeredMbti = it.getStringExtra("mbti") ?: ""

                    Toast.makeText(context, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome To Sopt",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(bottom = 240.dp)
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
            placeholder = { Text("사용자 이름 입력") },
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
            placeholder = { Text("비밀번호 입력") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "회원가입하기",
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                val intent = Intent(context, SignUpActivity::class.java)
                signUpLauncher.launch(intent)
            }
        )
        Button(
            onClick = {
                when {
                    id.isBlank() || pw.isBlank() ->
                        Toast.makeText(context, "ID와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()

                    id == registeredId && pw == registeredPw -> {
                        Toast.makeText(context, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()

                        // MainActivity로 이동
                        val intent = Intent(context, MainActivity::class.java).apply {
                            putExtra("id", registeredId)
                            putExtra("pw", registeredPw)
                            putExtra("nickname", registeredNickname)
                            putExtra("mbti", registeredMbti)
                        }
                        context.startActivity(intent)
                    }

                    else -> Toast.makeText(context, "ID 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "로그인 하기",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    DiveTheme {
        LoginScreen()
    }
}