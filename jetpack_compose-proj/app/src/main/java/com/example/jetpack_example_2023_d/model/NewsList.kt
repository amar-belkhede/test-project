package com.example.jetpack_example_2023_d.model

data class NewsList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)