package com.sopt.dive

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.ui.theme.DiveTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.component.DiveBottomBar
import com.sopt.dive.home.homeNavGraph
import com.sopt.dive.my.myNavGraph
import com.sopt.dive.search.searchNavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.sopt.dive.data.ResponseUserListDto
import com.sopt.dive.data.ServicePool
import com.sopt.dive.home.navigateToHome
import com.sopt.dive.login.Login
import com.sopt.dive.login.loginNavGraph
import com.sopt.dive.login.navigateToLogin
import com.sopt.dive.signup.navigateToSignUp
import com.sopt.dive.signup.signUpNavGraph
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private val userService by lazy { ServicePool.userService }
    private val userListState = mutableStateOf<ResponseUserListDto?>(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUserList(page = 2)

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserListScreen(
                        userList = userListState.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun getUserList(page: Int) {
        userService.getUserList(page = page).enqueue(object
            : Callback<ResponseUserListDto> {
            override fun onResponse(
                call: Call<ResponseUserListDto>,
                response: Response<ResponseUserListDto?>
            ) {
                if (response.isSuccessful) {
                    userListState.value = response.body()
                } else {
                    val error = response.message()
                    Log.e("error", error.toString())
                }
            }

            override fun onFailure(p0: Call<ResponseUserListDto>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }
        })
    }
}

@Composable
fun UserListScreen(
    userList: ResponseUserListDto?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        userList?.data?.forEach { user ->
            Text(text = "${user.firstName} ${user.lastName}")
        }
    }
}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            DiveTheme {
//                MainScreen()
//            }
//        }
//    }
//}

@Composable
fun DiveNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Login,
        modifier = modifier
    ) {
        loginNavGraph(
            navigateToSignUp = navController::navigateToSignUp,
            navigateToHome = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                )
            }
        )
        signUpNavGraph(
            navigateToLogin = navController::navigateToLogin
        )
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
    Log.d("MainScreen", "currentRoute = $currentRoute")

    val currentTab = tabs.find { it.route == currentRoute } ?: MainTab.HOME

    val bottomBarRoutes = listOf(
        MainTab.HOME.route,
        MainTab.SEARCH.route,
        MainTab.MYPAGE.route
    )

    val showBottomBar = bottomBarRoutes.contains(currentRoute)

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                DiveBottomBar(
                    visible = true,
                    tabs = tabs,
                    currentTab = currentTab,
                    onTabSelected = { tab ->
                        navController.navigate(tab.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        DiveNavHost(
            navController = navController,
            paddingValues = paddingValues,
        )
    }
}