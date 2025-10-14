package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                SignInScreen()
            }
        }
    }
}

@Composable
fun Boxes() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Red)
        )
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Blue)
        )

    }
}

@Composable
fun SignInScreen() {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    val context = LocalContext.current

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
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "회원가입하기",
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .clickable(
                    onClick = {
                    val intent = Intent(context, SignUpActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                    }
                )
        )
        Button(
            onClick = {},
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
fun BoxesPreview() {
    DiveTheme {
        Boxes()
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    DiveTheme {
        SignInScreen()
    }
}