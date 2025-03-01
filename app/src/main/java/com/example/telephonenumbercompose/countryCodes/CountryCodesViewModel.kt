package com.example.telephonenumbercompose.countryCodes

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.telephonenumbercompose.model.CountryModel
import com.example.telephonenumbercompose.model.countryList
import com.example.telephonenumbercompose.model.defaultCountryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CountryCodesViewModel @Inject constructor(private val sp : SharedPreferences): ViewModel() {
    private var _state = MutableStateFlow(CountryCodesState())
    val state = _state.asStateFlow()

    private var _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        retrieveSelectedCountryCode()
        observeSearchQuery()
    }

    fun onAction(intent : CountryCodesIntent) {
        when(intent) {
            is CountryCodesIntent.OnCountryCodeSelected -> {
                onCountryCodeSelected(selected = intent.countryModel)
            }
            is CountryCodesIntent.OnSearchQueryChange -> {
                onSearchQueryChange(query = intent.searchQuery)
            }
            is CountryCodesIntent.FetchSearchResults -> observeSearchQuery()
        }
    }

    private fun onCountryCodeSelected(selected : CountryModel) {
        saveSelectedCountryCode(countryName = selected.name)
        _state.update { state -> state.copy(countryModel = selected) }
    }

    private fun onSearchQueryChange(query : String) {
        _searchQuery.value = query
    }

    private fun updateCountryList(data : List < CountryModel >) {
        _state.update { state -> state.copy(countryList = data) }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        _searchQuery
            .debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                if (query.isEmpty()) updateCountryList(data = countryList)
                else {
                    val filteredList = countryList.filter { country ->
                        country.name.contains(query, ignoreCase = true) ||
                                country.code.contains(query, ignoreCase = true) ||
                                country.dialCode.contains(query, ignoreCase = true)
                    }
                    updateCountryList(data = filteredList)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun saveSelectedCountryCode(countryName : String) {
        val editor = sp.edit()
        editor.putString("selected_country_name", countryName)
        editor.apply()
    }

    private fun retrieveSelectedCountryCode() {
        val countryName = sp.getString("selected_country_name", defaultCountryModel.name)
            ?: defaultCountryModel.name
        val countryModel = countryList.find { country -> country.name == countryName } ?: defaultCountryModel
        _state.update { state -> state.copy(countryModel = countryModel) }
    }
}