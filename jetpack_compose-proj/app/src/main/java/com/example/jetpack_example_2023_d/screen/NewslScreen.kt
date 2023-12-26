package com.example.jetpack_example_2023_d.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpack_example_2023_d.model.Article
import com.example.jetpack_example_2023_d.viewmodel.NewsViewModel

@Composable
fun NewsScreen() {
    val viewModel: NewsViewModel = hiltViewModel()
    val newsList = viewModel.newsList.collectAsState()
    
    LazyColumn{
        items(newsList.value){
            NewsCard(article = it)
        }
    }
}

@Composable
fun NewsCard(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color.LightGray),
    ) {
        Text(
                text = article.title,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
    }
}