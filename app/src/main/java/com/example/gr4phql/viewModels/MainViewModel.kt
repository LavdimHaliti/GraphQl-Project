package com.example.gr4phql.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.gr4phql.ContinentsAndCountriesListQuery
import com.example.gr4phql.CountriesListQuery
import com.example.gr4phql.CountryQuery
import com.example.gr4phql.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private val _continentListLiveData =
        MutableLiveData<ApolloResponse<ContinentsAndCountriesListQuery.Data>>()
    val continentListLiveData: LiveData<ApolloResponse<ContinentsAndCountriesListQuery.Data>> get() = _continentListLiveData

    private val _countriesListLiveData = MutableLiveData<ApolloResponse<CountriesListQuery.Data>>()
    val countriesListLiveData: LiveData<ApolloResponse<CountriesListQuery.Data>> get() = _countriesListLiveData

    private val _countryLiveData = MutableLiveData<ApolloResponse<CountryQuery.Data>>()
    val countryLiveData: LiveData<ApolloResponse<CountryQuery.Data>> get() = _countryLiveData

    fun getContinentList() = viewModelScope.launch {
        try {
            val response = repository.getContinentList()
            _continentListLiveData.postValue(response)
        } catch (exception: ApolloException) {
            Log.d("error", exception.message.toString(), exception)
        }
    }

    fun getCountriesList() = viewModelScope.launch {
        try {
            val response = repository.getCountryList()
            _countriesListLiveData.postValue(response)
        } catch (exception: ApolloException) {
            Log.d("error", exception.message.toString(), exception)
        }
    }

    fun getCountry(id: String) = viewModelScope.launch {
        try {
            val response = repository.getCountry(id)
            _countryLiveData.postValue(response)
        } catch (exception: ApolloException) {
            Log.d("error", exception.message.toString(), exception)
        }
    }
}