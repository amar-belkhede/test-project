package com.example.stock_market_app.data.repository

import com.example.stock_market_app.data.csv.CompanyListingsParser
import com.example.stock_market_app.data.local.StockDatabase
import com.example.stock_market_app.data.mapper.toCompanyListing
import com.example.stock_market_app.data.mapper.toCompanyListingEntity
import com.example.stock_market_app.data.remote.StockApi
import com.example.stock_market_app.domain.model.CompanyListing
import com.example.stock_market_app.domain.repository.StockRepository
import com.example.stock_market_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingsParser: CompanyListingsParser
) : StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCrash = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCrash) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListing = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
                null
            }

            remoteListing?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }

        }
    }
}











