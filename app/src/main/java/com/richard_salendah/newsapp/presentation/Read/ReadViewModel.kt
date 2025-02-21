package com.richard_salendah.newsapp.presentation.Read

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: ReadEvent) {

        when (event) {
            is ReadEvent.InsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticle(url = event.article.url)
                    if (article == null) {
                        insertArticle(article = event.article)
                    } else {
                        deleteArticle(article = event.article)
                    }
                }

            }

            is ReadEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }

    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = "Article deleted"
    }

    private suspend fun insertArticle(article: Article) {
        newsUseCases.insertArticle(article = article)
        sideEffect = "Article saved"
    }
}