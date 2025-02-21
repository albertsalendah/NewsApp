package com.richard_salendah.newsapp.domain.usecase

data class NewsUseCases(
    val searchNews: SearchNews,
    val insertArticle: InsertArticle,
    val deleteArticle: DeleteArticle,
    val getAllArticles: GetAllArticles,
    val getArticle: GetArticle
)
