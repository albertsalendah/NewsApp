package com.richard_salendah.newsapp.domain.usecase

import androidx.paging.PagingData
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}