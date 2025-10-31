package com.sopt.dive.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun MyScreen(id: String, pw: String, nickname: String, mbti: String) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Profile Image",
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "$nickname 님",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "ID",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = id,
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "PW",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = pw,
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "NICKNAME",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = nickname,
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "MBTI",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = mbti,
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPreview() {
    DiveTheme {
        MyScreen("honggill", "dong1234", "홍길동", "ENFP")
    }
}