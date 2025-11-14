package com.sopt.dive.home

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.sopt.dive.R
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.UserInfo
import com.sopt.dive.util.UserPreferences
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.util.UiState

data class Profile(
    val imageUrl: String,
    val name: String,
    val description: String
)

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val userPrefs = UserPreferences(context)
    val userId = userPrefs.getUserId()

    val profiles by viewModel.profiles.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userId) {
        viewModel.loadUserInfo(userId)
    }

    when (uiState) {
        is UiState.Success -> {
            val data = (uiState as UiState.Success<HomeUiState>).data
            HomeScreen(
                name = data.name,
                profiles = profiles,
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
fun HomeScreen(
    name: String?,
    profiles: List<Profile>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Home",
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row (
            modifier = Modifier.fillMaxWidth(),
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
                text = name ?: "",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(profiles) { profiles ->
                ProfileCard(profile = profiles)
            }
        }
    }
}

@Composable
private fun ProfileCard(profile: Profile) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(profile.imageUrl),
                contentDescription = profile.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(32.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = profile.name,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = profile.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    DiveTheme {
        HomeScreen(
            name = "테스트",
            profiles = List(5) { index ->
                Profile(
                    imageUrl = "",
                    name = "안두콩${index + 1}",
                    description = "내용 ${index + 1}"
                )
            }
        )
    }
}
