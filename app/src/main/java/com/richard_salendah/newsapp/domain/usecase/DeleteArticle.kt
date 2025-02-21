package com.richard_salendah.newsapp.domain.usecase

import com.richard_salendah.newsapp.data.local_data_resource.NewsLocalDataSource
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.domain.repository.NewsRepository

class DeleteArticle (
    private val repository: NewsRepository
){
    suspend operator fun invoke(article: Article){
        repository.deleteArticle(article)
    }
}