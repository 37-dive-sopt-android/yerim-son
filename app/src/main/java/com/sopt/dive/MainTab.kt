package com.sopt.dive

import androidx.annotation.DrawableRes
import com.sopt.dive.home.Home
import com.sopt.dive.search.Search
import com.sopt.dive.my.My
import com.sopt.dive.navigation.MainTabRoute

enum class MainTab(
    @DrawableRes val iconRes: Int,
    val route: MainTabRoute,
    val label: String
) {
    HOME(
        iconRes = R.drawable.ic_home,
        route = Home,
        label = "홈"
    ),
    SEARCH(
        iconRes = R.drawable.ic_search,
        route = Search,
        label = "검색"
    ),
    MYPAGE(
        iconRes = R.drawable.ic_my,
        route = My,
        label = "마이페이지"
    )
}
