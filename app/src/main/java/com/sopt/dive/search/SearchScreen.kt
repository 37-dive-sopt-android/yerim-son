package com.sopt.dive.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SearchRoute(
    paddingValues: PaddingValues
) {
    SearchScreen(
        modifier = Modifier
            .padding(paddingValues)
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlipCard(modifier = Modifier.width(300.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    DiveTheme {
        SearchScreen()
    }
}