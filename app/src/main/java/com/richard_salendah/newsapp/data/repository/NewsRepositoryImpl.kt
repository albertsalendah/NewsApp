package com.richard_salendah.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.richard_salendah.newsapp.data.local_data_resource.NewsLocalDataSource
import com.richard_salendah.newsapp.data.remote_data_source.NewsRemoteDataSource
import com.richard_salendah.newsapp.data.remote_data_source.NewsRemoteDataSourceImpl
import com.richard_salendah.newsapp.data.remote_data_source.NewsSearchRemoteDataSourceImpl
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsSearchRemoteDataSourceImpl(
                    newsRemoteDataSource = remoteDataSource,
                    searchQuery = searchQuery,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun insertArticle(article: Article) {
        newsLocalDataSource.insert(article = article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsLocalDataSource.delete(article = article)
    }

    override suspend fun getArticle(url: String): Article? {
        return newsLocalDataSource.getArticle(url = url)
    }

    override fun getAllArticle(): Flow<List<Article>> {
        return newsLocalDataSource.getAllArticles()
    }
}