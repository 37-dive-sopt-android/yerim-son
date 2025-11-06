package com.sopt.dive.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Login

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Login,
        navOptions = navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    navigateToSignUp: () -> Unit,
    navigateToHome: (String, String) -> Unit
) {
    composable<Login> {
        LoginRoute(
            onSignUpClick = navigateToSignUp,
            onLoginClick = navigateToHome
        )
    }
}