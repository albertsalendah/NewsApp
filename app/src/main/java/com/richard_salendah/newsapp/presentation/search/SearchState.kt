package com.richard_salendah.newsapp.presentation.search

import androidx.paging.PagingData
import com.richard_salendah.newsapp.domain.entity.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {
}