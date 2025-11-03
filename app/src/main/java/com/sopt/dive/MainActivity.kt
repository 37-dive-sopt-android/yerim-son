package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.dive.ui.theme.DiveTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.component.DiveBottomBar
import com.sopt.dive.home.homeNavGraph
import com.sopt.dive.my.myNavGraph
import com.sopt.dive.search.searchNavGraph
import androidx.navigation.compose.NavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun DiveNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainTab.HOME.route,
        modifier = modifier
    ) {
        homeNavGraph(paddingValues = paddingValues)
        searchNavGraph(paddingValues = paddingValues)
        myNavGraph(paddingValues = paddingValues)
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val tabs = MainTab.entries

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val currentRoute = currentDestination?.route ?: MainTab.HOME.route

    val currentTab = tabs.find { tab ->
        tab.route == currentRoute
    } ?: MainTab.HOME

    Scaffold(
        bottomBar = {
            DiveBottomBar(
                tabs = tabs,
                currentTab = currentTab,
                onTabSelected = { tab ->
                    navController.navigate(tab.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(MainTab.HOME.route) { saveState = true }
                    }
                }
            )
        }
    ) { paddingValues ->
        DiveNavHost(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}
