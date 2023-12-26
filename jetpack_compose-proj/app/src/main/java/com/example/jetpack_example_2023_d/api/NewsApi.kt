package com.example.jetpack_example_2023_d.api

import com.example.jetpack_example_2023_d.BuildConfig
import com.example.jetpack_example_2023_d.model.NewsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun newsList(@Query("country") country: String = "in",
                         @Query("category") category: String = "technology",
                         @Query("apiKey") apiKey: String = BuildConfig.API_KEY,): Response<NewsList>

}