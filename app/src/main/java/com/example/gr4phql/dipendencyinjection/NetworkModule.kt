package com.example.gr4phql.dipendencyinjection

import com.example.gr4phql.api.ApolloClientBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApolloClient() = ApolloClientBuilder()
}