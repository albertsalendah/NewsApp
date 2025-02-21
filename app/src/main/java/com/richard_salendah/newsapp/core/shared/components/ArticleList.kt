package com.richard_salendah.newsapp.core.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.richard_salendah.newsapp.core.theme.AppTheme
import com.richard_salendah.newsapp.core.utils.Dimens.ExtraSmallPadding2
import com.richard_salendah.newsapp.core.utils.Dimens.MediumPadding1
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.domain.entity.Source


@Composable
fun ArticlesListOffline(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()) {
        EmptyScreen()
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(articles.size) { index ->
                val article = articles[index]

                if (index % 5 == 0) {
                    ArticleCard(
                        article = article,
                        onClick = { onClick(article) },
                        modifier = Modifier.fillMaxWidth()
                    )
                } else if (index % 5 in 1..4 && index % 2 != 0 && index + 1 < articles.size) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        ArticleCard(
                            article = article,
                            onClick = { onClick(article) },
                            modifier = Modifier
                                .weight(1f)
                                .height(IntrinsicSize.Min)
                                .padding(4.dp)
                        )
                        articles.getOrNull(index + 1)?.let { nextArticle ->
                            ArticleCard(
                                article = nextArticle,
                                onClick = { onClick(nextArticle) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(IntrinsicSize.Min)
                                    .padding(4.dp)
                            )
                        }
                    }
                } else if (index % 5 in 1..4 && index % 2 == 0) {
                } else {
                    ArticleCard(
                        article = article,
                        onClick = { onClick(article) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@Composable
fun ArticlesListOnline(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {

            items(articles.itemCount) { index ->
                articles[index]?.let { item ->
                    val actualIndex = remember { articles.itemSnapshotList.indexOf(item) }

                    if (actualIndex % 5 == 0) {
                        ArticleCard(
                            article = item,
                            onClick = { onClick(item) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else if (actualIndex % 5 in 1..4 && (actualIndex % 2 != 0)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min),
                        ) {
                            articles[index]?.let { smallItem1 ->
                                ArticleCard(
                                    article = smallItem1,
                                    onClick = { onClick(smallItem1) },
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .weight(1f)
                                        .padding(2.dp)
                                )
                            }
                            articles[index + 1]?.let { smallItem2 ->
                                ArticleCard(
                                    article = smallItem2,
                                    onClick = { onClick(smallItem2) },
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .weight(1f)
                                        .padding(2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        articles.itemCount == 0 -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            ArticleShimmer(modifier = Modifier.padding(horizontal = MediumPadding1))
        }
    }
}

