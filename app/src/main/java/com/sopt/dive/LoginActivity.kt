package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import com.sopt.dive.util.DiveValidator

class LoginActivity : ComponentActivity() {
    private var registeredId: String = ""
    private var registeredPw: String = ""
    private var registeredNickname: String = ""
    private var registeredMbti: String = ""

    // registerForActivityResult로 회원가입 결과 받기
    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                data?.let {
                    registeredId = it.getStringExtra("id") ?: ""
                    registeredPw = it.getStringExtra("pw") ?: ""
                    registeredNickname = it.getStringExtra("nickname") ?: ""
                    registeredMbti = it.getStringExtra("mbti") ?: ""

                    Toast.makeText(this, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                LoginScreen(
                    onSignUpClick = {
                        val intent = Intent(this, SignUpActivity::class.java)
                        signUpLauncher.launch(intent)
                    },
                    onLoginClick = { id, pw ->
                        val result = DiveValidator.validateLogin(id, pw)
                        if (!result.isValid) {
                            Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                        } else if (id == registeredId && pw == registeredPw) {
                            Toast.makeText(this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()

                                // MainActivity로 이동
                                val intent = Intent(this, MainActivity::class.java).apply {
                                    putExtra("id", registeredId)
                                    putExtra("pw", registeredPw)
                                    putExtra("nickname", registeredNickname)
                                    putExtra("mbti", registeredMbti)
                                }
                                this.startActivity(intent)
                        } else {
                            Toast.makeText(this, "ID 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onLoginClick: (String, String) -> Unit
) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

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
                .padding(top = 20.dp, bottom = 240.dp)
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
            modifier = Modifier.clickable { onSignUpClick() }
        )
        Button(
            onClick = { onLoginClick(id, pw) },
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
private fun LoginPreview() {
    DiveTheme {
        LoginScreen(
            onSignUpClick = {},
            onLoginClick = { _, _ -> }
        )
    }
}