package com.richard_salendah.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.richard_salendah.newsapp.core.shared.components.ArticlesListOnline
import com.richard_salendah.newsapp.core.shared.components.SearchBar
import com.richard_salendah.newsapp.core.utils.Dimens.MediumPadding1
import com.richard_salendah.newsapp.domain.entity.Article

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToRead: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) })

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            val isRefreshing = articles.loadState.refresh is LoadState.Loading

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { articles.refresh() } // Refreshing the PagingData
            ) {
                ArticlesListOnline(articles = articles, onClick = {
                    navigateToRead(it)
                })
            }
        }
    }
}