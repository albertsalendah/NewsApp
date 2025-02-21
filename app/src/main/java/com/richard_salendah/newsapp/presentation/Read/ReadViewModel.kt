package com.richard_salendah.newsapp.presentation.Read

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked.asStateFlow()

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: ReadEvent) {

        when (event) {
            is ReadEvent.InsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticle(url = event.article.url)
                    if (article == null) {
                        insertArticle(article = event.article)
                        _isBookmarked.value = true
                    } else {
                        deleteArticle(article = event.article)
                        _isBookmarked.value = false
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
        _isBookmarked.value = false
        sideEffect = "Article deleted"
    }

    private suspend fun insertArticle(article: Article) {
        newsUseCases.insertArticle(article = article)
        _isBookmarked.value = true
        sideEffect = "Article saved"
    }

    fun checkIfArticleIsBookmarked(url: String) {
        viewModelScope.launch {
            val article = newsUseCases.getArticle(url)
            _isBookmarked.value = article != null
        }
    }
}