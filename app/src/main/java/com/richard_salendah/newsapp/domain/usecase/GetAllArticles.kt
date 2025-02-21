package com.richard_salendah.newsapp.domain.usecase

import com.richard_salendah.newsapp.data.local_data_resource.NewsLocalDataSource
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetAllArticles(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getAllArticle()
    }
}