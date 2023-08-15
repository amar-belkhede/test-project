package com.example.stock_market_app.data.csv

import com.example.stock_market_app.domain.model.CompanyListing
import java.io.InputStream

interface CSVParser {
    suspend fun parse(stream: InputStream): List<CompanyListing>
}