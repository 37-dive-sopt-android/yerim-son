package com.sopt.dive.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.component.LabeledInfoText
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun MyRoute(
    paddingValues: PaddingValues
) {
    MyScreen(
        id = "test12",
        pw = "12341234",
        nickname = "안두콩",
        mbti = "ENFP",
        modifier = Modifier
            .padding(paddingValues)
    )
}

@Composable
fun MyScreen(
    id: String,
    pw: String,
    nickname: String,
    mbti: String,
    modifier: Modifier
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Text(
            text = "My",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

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
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        LabeledInfoText(label = "ID", value = id)
        LabeledInfoText(label = "PW", value = pw)
        LabeledInfoText(label = "NICKNAME", value = nickname)
        LabeledInfoText(label = "MBTI", value = mbti)
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPreview() {
    DiveTheme {
        MyScreen("honggill", "dong1234", "홍길동", "ENFP", modifier = Modifier)
    }
}