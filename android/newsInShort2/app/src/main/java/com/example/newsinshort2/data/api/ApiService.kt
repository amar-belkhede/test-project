package com.example.newsinshort2.data.api

import com.example.newsinshort2.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "29cd529d0d874ae598bb67827e5d40c1",
    ): Response<NewsResponse>
}


//https://newsapi.org/v2/top-headlines?country=us&apiKey=29cd529d0d874ae598bb67827e5d40c1