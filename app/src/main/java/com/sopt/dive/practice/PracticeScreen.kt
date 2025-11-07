package com.sopt.dive.practice

import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.practice.PracticeViewModel
import com.sopt.dive.ui.theme.DiveTheme

enum class BoxState { Collapsed, Expanded }

@Composable
fun PracticeScreen(
    viewModel: PracticeViewModel = viewModel()
) {
    val count = viewModel.count

    val context = LocalContext.current
    LaunchedEffect(count) {
        if (count >= 5) {
            Toast.makeText(context, "5 이상", Toast.LENGTH_SHORT).show()
        }
    }

    var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(currentState, label = "transition")

    val boxSize by transition.animateDp(label = "boxSize") { state ->
        when (state) {
            BoxState.Collapsed -> 100.dp
            BoxState.Expanded -> 200.dp
        }
    }

    val color by transition.animateColor(label = "color") { state ->
        when (state) {
            BoxState.Collapsed -> Color.Blue
            BoxState.Expanded -> Color.Red
        }
    }

    val rotate by transition.animateFloat(label = "rotate") { state ->
        when (state) {
            BoxState.Collapsed -> 0f
            BoxState.Expanded -> 45f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .rotate(rotate)
                .background(color)
                .clickable {
                    currentState = when (currentState) {
                        BoxState.Expanded -> BoxState.Collapsed
                        BoxState.Collapsed -> BoxState.Expanded
                    }
                }
        )

        Text(
            text = "Count $count",
            fontSize = 24.sp
        )

        Button(
            modifier = Modifier,
            onClick = { viewModel.onButtonClick() },
            enabled = viewModel.isButtonEnabled
        ) {
            Text("Click me!")
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PracticePreview() {
    DiveTheme {
        PracticeScreen()
    }
}