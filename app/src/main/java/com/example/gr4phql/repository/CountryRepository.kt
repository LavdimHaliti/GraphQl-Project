package com.example.gr4phql.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.example.gr4phql.ContinentsAndCountriesListQuery
import com.example.gr4phql.CountriesListQuery
import com.example.gr4phql.CountryQuery
import com.example.gr4phql.api.ApolloClientBuilder
import com.example.gr4phql.api.CountryApi
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val apolloClientBuilder: ApolloClientBuilder
) : CountryApi {

    override suspend fun getContinentList(): ApolloResponse<ContinentsAndCountriesListQuery.Data> {
        return apolloClientBuilder.createApolloClient().query(ContinentsAndCountriesListQuery())
            .execute()
    }

    override suspend fun getCountryList(): ApolloResponse<CountriesListQuery.Data> {
        return apolloClientBuilder.createApolloClient().query(CountriesListQuery()).execute()
    }

    override suspend fun getCountry(id: String): ApolloResponse<CountryQuery.Data> {
        return apolloClientBuilder.createApolloClient().query(CountryQuery(id)).execute()
    }

}