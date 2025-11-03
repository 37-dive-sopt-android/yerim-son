package com.sopt.dive.search

import androidx.compose.foundation.layout.PaddingValues
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

fun NavGraphBuilder.searchNavGraph(
    paddingValues: PaddingValues
) {
    composable<Search> {
        SearchRoute(
            paddingValues = paddingValues
        )
    }
}
