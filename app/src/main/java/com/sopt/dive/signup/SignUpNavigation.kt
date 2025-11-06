package com.sopt.dive.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SignUp

fun NavController.navigateToSignUp(
    navOptions: NavOptions? = null
) {
    navigate(
        route = SignUp,
        navOptions = navOptions)
}

fun NavGraphBuilder.signUpNavGraph(
    navigateToLogin: () -> Unit
) {
    composable<SignUp> {
        SignUpRoute(
            onSignUpSuccess = { _, _, _, _ ->
                navigateToLogin()
            }
        )
    }
}
