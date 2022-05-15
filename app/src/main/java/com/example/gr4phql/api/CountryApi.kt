package com.example.gr4phql.api

import com.apollographql.apollo3.api.ApolloResponse
import com.example.gr4phql.ContinentsAndCountriesListQuery
import com.example.gr4phql.CountriesListQuery
import com.example.gr4phql.CountryQuery

interface CountryApi {

    suspend fun getContinentList(): ApolloResponse<ContinentsAndCountriesListQuery.Data>
    suspend fun getCountryList(): ApolloResponse<CountriesListQuery.Data>
    suspend fun getCountry(id: String): ApolloResponse<CountryQuery.Data>

}