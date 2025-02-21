package com.richard_salendah.newsapp.data.model

import com.richard_salendah.newsapp.domain.entity.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)