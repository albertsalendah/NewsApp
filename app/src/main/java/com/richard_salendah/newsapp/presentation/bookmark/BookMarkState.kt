package com.richard_salendah.newsapp.presentation.bookmark

import com.richard_salendah.newsapp.domain.entity.Article

data class BookMarkState(val articles: List<Article> = emptyList())