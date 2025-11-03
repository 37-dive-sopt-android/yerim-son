package com.sopt.dive.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sopt.dive.ui.theme.DiveTheme

data class Profile(
    val imageUrl: String,
    val name: String,
    val description: String
)

// 더미 데이터 생성
val dummyProfiles = List(20) { index ->
    Profile(
        imageUrl = "https://static.megamart.com/product/image/1392/13924003/13924003_1_960.jpg",
        name = "안두콩${index + 1}",
        description = "내용 ${index + 1}"
    )
}

@Composable
fun HomeRoute(
    paddingValues: PaddingValues
) {
    HomeScreen(
        profiles = dummyProfiles,
        modifier = Modifier
            .padding(paddingValues)
    )
}


@Composable
fun HomeScreen(
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
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(profiles.size) { index ->
                ProfileCard(profile = profiles[index])
            }
        }
    }
}

@Composable
fun ProfileCard(profile: Profile) {
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
                Text(text = profile.name, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = profile.description, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    DiveTheme {
        HomeScreen(profiles = dummyProfiles)
    }
}
