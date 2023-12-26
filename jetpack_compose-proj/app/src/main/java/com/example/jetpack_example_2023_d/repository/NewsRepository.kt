package com.example.jetpack_example_2023_d.repository

import com.example.jetpack_example_2023_d.api.NewsApi
import com.example.jetpack_example_2023_d.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsApi: NewsApi) {

    private var _newsList = MutableStateFlow<List<Article>>(emptyList())
    val newsList: StateFlow<List<Article>>
        get() {return _newsList}

    val categoryList = listOf("business","entertainment","general","science","health","technology")
    suspend fun getNewsList(country: String = "in",
                            category: String = "technology") {
        val response = newsApi.newsList(country = country, category = category)

        if (response.isSuccessful && response.body() != null) {
            _newsList.emit(response.body()!!.articles)
        }
    }


}