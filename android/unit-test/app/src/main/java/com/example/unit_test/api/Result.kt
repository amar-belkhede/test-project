package com.example.unit_test.api

import kotlin.Result

sealed class Result {
    data class Success(val result: String): com.example.unit_test.api.Result()
    data class Failure(val error: String): com.example.unit_test.api.Result()
}