package com.richard_salendah.newsapp.data.remote_data_source

import com.richard_salendah.newsapp.core.Constants.API_KEY
import com.richard_salendah.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRemoteDataSource {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}