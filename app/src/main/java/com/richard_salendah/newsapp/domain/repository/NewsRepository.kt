package com.richard_salendah.newsapp.domain.repository

import androidx.paging.PagingData
import com.richard_salendah.newsapp.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun getArticle(url: String): Article?
    fun getAllArticle(): Flow<List<Article>>

}