package com.example.newsinshort2.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsinshort2.data.entity.NewsResponse
import com.example.newsinshort2.ui.components.NewsRowComponent
import com.example.newsinshort2.ui.components.Loader
import com.example.newsinshort2.ui.components.NewsList
import com.example.newsinshort2.ui.viewmodel.NewsViewModel
import com.example.newsinshort2.utility.ResourceState


const val TAG = "HomeScreen"
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val newsResponse by newsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
    ) {
        100
    }

    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp,
    ) {page: Int ->
        when(newsResponse) {
            is ResourceState.Loading  -> {
                Log.d(TAG, "inside loading")
                Loader()
            }
            is ResourceState.Success -> {
                Log.d(TAG, "inside success")
                val response = (newsResponse as ResourceState.Success<NewsResponse>).data
                if (response.articles.isNotEmpty()) {
                    NewsRowComponent(page, response.articles[page])
                }
            }
            is ResourceState.Error -> {
                Log.d(TAG, "inside error")
            }
        }
        
    }

//    Surface(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        when(newsResponse) {
//            is ResourceState.Loading  -> {
//                Log.d(TAG, "inside loading")
//                Loader()
//            }
//            is ResourceState.Success -> {
//                Log.d(TAG, "inside success")
//                val response = (newsResponse as ResourceState.Success<NewsResponse>).data
//                NewsList(response)
//            }
//            is ResourceState.Error -> {
//                Log.d(TAG, "inside error")
//            }
//        }
//    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}