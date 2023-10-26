package com.example.newsinshort2.ui.repository

import android.util.Log
import com.example.newsinshort2.data.datasource.NewsDataSource
import com.example.newsinshort2.data.entity.NewsResponse
import com.example.newsinshort2.utility.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsReposiory @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

//    suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
//        return newsDataSource.getNewsHeadline(country)
//    }

    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())
            val response = newsDataSource.getNewsHeadline(country)

            if (response.isSuccessful && response.body() != null) {
                Log.e(TAG, response.body()!!.toString())
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error Fetching news data"))
                Log.e(TAG, response.message())
            }
        } .catch {
            Log.e(TAG, it.toString())
            emit(ResourceState.Error(it.localizedMessage ?: "Some error in flow"))
        }
    }

    companion object {
        const val TAG = "NewsRepository"
    }
}