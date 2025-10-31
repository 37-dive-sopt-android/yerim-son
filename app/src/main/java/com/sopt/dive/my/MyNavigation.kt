package com.sopt.dive.my

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object My : MainTabRoute

fun NavController.navigateToMy(
    navOptions: NavOptions? = null
) {
    navigate(
        route = My,
        navOptions = navOptions)
}

fun NavGraphBuilder.myNavGraph() {
    composable<My> {
        MyRoute()
    }
}
