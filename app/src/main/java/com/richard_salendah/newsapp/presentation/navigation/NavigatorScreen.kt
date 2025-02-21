package com.richard_salendah.newsapp.presentation.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.richard_salendah.newsapp.R
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.presentation.Read.ReadEvent
import com.richard_salendah.newsapp.presentation.Read.ReadScreen
import com.richard_salendah.newsapp.presentation.Read.ReadViewModel
import com.richard_salendah.newsapp.presentation.bookmark.BookMarkScreen
import com.richard_salendah.newsapp.presentation.bookmark.BookMarkViewModel
import com.richard_salendah.newsapp.presentation.navigation.components.BottomNavigation
import com.richard_salendah.newsapp.presentation.navigation.components.BottomNavigationItem
import com.richard_salendah.newsapp.presentation.search.SearchScreen
import com.richard_salendah.newsapp.presentation.search.SearchViewModel

@Composable
fun NavigatorScreen() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_search, label = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, label = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    selectedItem = remember(key1 = backStackEntry) {
        when (backStackEntry?.destination?.route) {
            Route.SearchScreen.route -> 0
            Route.BookMarkScreen.route -> 1
            else -> 0
        }
    }

    val isBottonNavVisible = remember(key1 = backStackEntry) {
        backStackEntry?.destination?.route == Route.SearchScreen.route ||
                backStackEntry?.destination?.route == Route.BookMarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottonNavVisible) {
                BottomNavigation(
                    items = bottomNavigationItems,
                    selectedItemIndex = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Route.SearchScreen.route)
                            1 -> navigateToTab(navController, Route.BookMarkScreen.route)
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.SearchScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding),
        ) {
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToRead = { article ->
                        navigateToRead(navController, article = article)
                    })
            }
            composable(route = Route.ReadScreen.route) {
                val viewModel: ReadViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(ReadEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        LaunchedEffect(article) {
                            viewModel.checkIfArticleIsBookmarked(article.url)
                        }
                        val isBookmarked by viewModel.isBookmarked.collectAsState()
                        ReadScreen(
                            article = article,
                            isBookmarked = isBookmarked,
                            event = viewModel::onEvent,
                            navigate = { navController.navigateUp() }
                        )
                    }
            }

            composable(route = Route.BookMarkScreen.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookMarkScreen(
                    state = state,
                    navigateToRead = { article ->
                        navigateToRead(navController, article = article)
                    })
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToRead(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.ReadScreen.route
    )
}