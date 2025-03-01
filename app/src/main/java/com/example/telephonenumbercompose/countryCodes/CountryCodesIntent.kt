package com.example.telephonenumbercompose.countryCodes

import com.example.telephonenumbercompose.model.CountryModel

sealed interface CountryCodesIntent {
    data class OnCountryCodeSelected(val countryModel : CountryModel) : CountryCodesIntent
    data class OnSearchQueryChange(val searchQuery : String) : CountryCodesIntent
    data object FetchSearchResults : CountryCodesIntent
}