package com.example.jetpack_example_2023_d.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_example_2023_d.model.Article
import com.example.jetpack_example_2023_d.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle): ViewModel() {

    val category = MutableStateFlow(newsRepository.categoryList)
    val newsList: StateFlow<List<Article>>
        get() = newsRepository.newsList
    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "technology"
            newsRepository.getNewsList(category = category)
        }
    }

    fun setCategory(category: String){
        viewModelScope.launch {
            newsRepository.getNewsList(category = category)
        }
    }

}