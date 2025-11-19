package com.sopt.dive.my

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.component.LabeledInfoText
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.UserPreferences
import androidx.compose.runtime.getValue
import com.sopt.dive.util.UiState

@Composable
fun MyRoute(
    paddingValues: PaddingValues,
    viewModel: MyViewModel = viewModel()
) {
    val context = LocalContext.current
    val userPrefs = UserPreferences(context)
    val userId = userPrefs.getUserId()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userId) {
        viewModel.loadUserInfo(userId)
    }

    when (uiState) {
        is UiState.Success -> {
            val data = (uiState as UiState.Success<MyUiState>).data
            MyScreen(
                username = data.username,
                name = data.name,
                email = data.email,
                age = data.age,
                modifier = Modifier.padding(paddingValues)
            )
        }
        is UiState.Failure -> {
            LaunchedEffect(Unit) {
                Toast.makeText(context, "사용자 정보를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        else -> {}
    }

}

@Composable
fun MyScreen(
    username: String,
    name: String,
    email: String,
    age: Int,
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
                text = name,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        LabeledInfoText(label = "USERNAME", value = username)
        LabeledInfoText(label = "NAME", value = name)
        LabeledInfoText(label = "EMAIL", value = email)
        LabeledInfoText(label = "AGE", value = age.toString())
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPreview() {
    DiveTheme {
        MyScreen(
            username = "testUser",
            name = "테스트",
            email = "test@email.com",
            age = 25,
            modifier = Modifier
        )
    }
}