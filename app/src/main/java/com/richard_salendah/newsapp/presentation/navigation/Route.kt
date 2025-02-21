package com.richard_salendah.newsapp.presentation.navigation

sealed class Route(val route: String) {
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "searchScreen")
    object BookMarkScreen : Route(route = "bookmarkScreen")
    object ReadScreen : Route(route = "readScreen")
    object NewsNavigation : Route(route = "newsNavigation")
    object NewsNavigationScreen : Route(route = "newsNavigationScreen")
}