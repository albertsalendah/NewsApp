package com.richard_salendah.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.richard_salendah.newsapp.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var startDestination by mutableStateOf(Route.NewsNavigation.route)
        private set

    init {
        startDestination = Route.NewsNavigation.route
    }
}