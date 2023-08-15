package com.example.stock_market_app.presentation.company_listing

sealed class CompanyListingsEvent{
    object Referesh: CompanyListingsEvent()
    data class OnSearchQueryChange(val query: String): CompanyListingsEvent()
}
