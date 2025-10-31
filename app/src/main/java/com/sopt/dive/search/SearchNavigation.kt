package com.sopt.dive.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object Search : MainTabRoute

fun NavController.navigateToSearch(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Search,
        navOptions = navOptions)
}

fun NavGraphBuilder.searchNavGraph() {
    composable<Search> {
        SearchRoute()
    }
}
