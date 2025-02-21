package com.richard_salendah.newsapp.data.remote_data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.richard_salendah.newsapp.domain.entity.Article

class NewsSearchRemoteDataSourceImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val searchQuery: String,
    private val source: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = newsRemoteDataSource.searchNews(
                searchQuery = searchQuery,
                sources = source,
                page = page
            )
            totalNewsCount += response.articles.size
            val articles = response.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == response.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}