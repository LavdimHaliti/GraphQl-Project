package com.example.gr4phql.api

import android.os.Looper
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.gr4phql.utils.Constants
import okhttp3.OkHttpClient

class ApolloClientBuilder {

    fun createApolloClient(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
        }
        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.Builder()
            .serverUrl(Constants.COUNTRIES_BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }
}