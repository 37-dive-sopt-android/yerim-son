package com.sopt.dive.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.MainTab
import com.sopt.dive.ui.theme.DiveTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DiveBottomBar(
    visible: Boolean,
    tabs: List<MainTab>,
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .navigationBarsPadding()
                    .fillMaxWidth()
            ) {
                tabs.forEach { tab ->
                    key(tab.route) {
                        DiveBottomBarItem(
                            tab = tab,
                            selected = (tab == currentTab),
                            onClick = { onTabSelected(tab) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.DiveBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = tab.iconRes),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
            tint = if (selected) Color.Black else Color.LightGray
        )
        Text(
            text = tab.label,
            color = if (selected) Color.Black else Color.Gray,
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    DiveTheme {
        Column(modifier = Modifier) {
            DiveBottomBar(
                visible = true,
                tabs = MainTab.entries,
                currentTab = MainTab.HOME,
                onTabSelected = {}
            )
        }
    }
}