package com.example.newsinshort2.ui.viewmodel

import android.util.Log
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsinshort2.data.AppConstants
import com.example.newsinshort2.data.entity.NewsResponse
import com.example.newsinshort2.ui.repository.NewsReposiory
import com.example.newsinshort2.utility.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsReposiory: NewsReposiory
) : ViewModel() {

    private val _news: MutableStateFlow<ResourceState<NewsResponse>> = MutableStateFlow(ResourceState.Loading())
    val news: StateFlow<ResourceState<NewsResponse>> = _news

    init {
        getNews(AppConstants.COUNTRY)
    }

    fun getNews(country: String){
        viewModelScope.launch(Dispatchers.IO) {
            newsReposiory.getNewsHeadline(country)
                .collectLatest {
                    _news.value = it
                }
        }

    }
    companion object {
        const val TAG = "NewsViewModel"
    }
}