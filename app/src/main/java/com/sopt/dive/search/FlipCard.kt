package com.sopt.dive.search

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun FlipCard(
    modifier: Modifier = Modifier
) {
    val frontImageUrl = "https://i.pinimg.com/736x/ab/82/38/ab82385c93b58ab3773b2a1b3e98b001.jpg"
    val backImageUrl = "https://i.pinimg.com/736x/c7/09/ad/c709adf20fa323dc69a2e61866205f42.jpg"

    var isFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 1500)
    )

    AsyncImage(
        model = if (rotation <= 90f) frontImageUrl else backImageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(10.dp))
            .clickable { isFlipped = !isFlipped }
    )
}

@Preview
@Composable
private fun FlipCardPreview() {
    DiveTheme {
        FlipCard(modifier = Modifier)
    }
}