package com.richard_salendah.newsapp.presentation.Read

import com.richard_salendah.newsapp.domain.entity.Article

sealed class ReadEvent {
    data class  InsertDeleteArticle(val article: Article) : ReadEvent()
    data object  RemoveSideEffect : ReadEvent()
}