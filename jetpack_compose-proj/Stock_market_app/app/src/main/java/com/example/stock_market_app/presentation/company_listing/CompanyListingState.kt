package com.example.stock_market_app.presentation.company_listing

import com.example.stock_market_app.domain.model.CompanyListing

data class CompanyListingState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
)
