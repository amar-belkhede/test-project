package com.example.jetpack_example_2023_d

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SideEffect {

    @Composable
    fun ListComposable() {
        val categoryState = remember { mutableStateOf(emptyList<String>()) }
        LaunchedEffect(Unit){
            categoryState.value = fetchCategory()
        }

        LazyColumn() {
            items(categoryState.value) { item ->
                Text(text = item)
            }
        }
    }

    fun fetchCategory(): List<String> {
        return listOf("one","two","three")
    }

    @Composable
    fun Counter() {
        var count = remember {
            mutableIntStateOf(0)
        }
        LaunchedEffect(key1 = Unit, block ={
            Log.d("jetpack","current count: ${count.intValue}")
        })
        Button(
            onClick = { count.intValue++ }
        ) {
            Text(text = "Increment counter")
        }
    }

    @Composable
    fun LaunchEffectExample() {
        val count = remember {
            mutableIntStateOf(0)
        }

        LaunchedEffect(Unit) {
            Log.d("jetpack","Started..")
            try {
                for (i in 1..10) {
                    count.intValue++
                    delay(1000)
                }
            }catch (e: Exception) {
                Log.d("jetpack", e.toString())
            }
        }
        var text = "counter is running ${count.value}"
        if (count.value == 10) {
            text = "counter stopped"
        }

        Text(text = text)
    }

    @Composable
    fun CoroutineScopeExample() {
        val count = remember {
            mutableIntStateOf(0)
        }
        var scope = rememberCoroutineScope()

        var text = "counter is running ${count.value}"
        if(count.value == 10) {
            text= "counter stopped"
        }
        Column {
            Text(text = text)
            Button(onClick = {
                scope.launch {
                    Log.d("jetpack", "started...")
                    try {
                        for (i in 1..10){
                            count.intValue++
                            delay(1000)
                        }
                    }catch (e:Exception){
                        Log.d("jetpack", e.toString())
                    }
                }
            }) {

            }
        }
    }
}