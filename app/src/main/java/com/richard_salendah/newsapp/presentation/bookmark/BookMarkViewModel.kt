package com.richard_salendah.newsapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard_salendah.newsapp.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BookMarkState())
    val state : State<BookMarkState> = _state

    init {
        getArticles()
    }

    private fun getArticles(){
        newsUseCases.getAllArticles().onEach {
            _state.value = _state.value.copy(articles = it.asReversed())
        }.launchIn(viewModelScope)
    }
}