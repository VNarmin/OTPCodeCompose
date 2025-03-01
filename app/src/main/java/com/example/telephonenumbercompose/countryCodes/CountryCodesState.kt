package com.example.telephonenumbercompose.countryCodes

import com.example.telephonenumbercompose.R
import com.example.telephonenumbercompose.model.CountryModel
import com.example.telephonenumbercompose.model.defaultCountryModel

data class CountryCodesState (
    val countryModel : CountryModel = defaultCountryModel,
    val countryList : List < CountryModel > = listOf(
        CountryModel("AZ", "+994", "Azerbaijan", R.drawable.az),
        CountryModel("US", "+1", "United States", R.drawable.us),
        CountryModel("GB", "+44", "United Kingdom", R.drawable.gb),
        CountryModel("DE", "+49", "Germany", R.drawable.de),
        CountryModel("FR", "+33", "France", R.drawable.fr),
        CountryModel("IN", "+91", "India", R.drawable.india),
        CountryModel("JP", "+81", "Japan", R.drawable.jp),
        CountryModel("CN", "+86", "China", R.drawable.cn),
        CountryModel("BR", "+55", "Brazil", R.drawable.br),
        CountryModel("CA", "+1", "Canada", R.drawable.ca),
        CountryModel("AU", "+61", "Australia", R.drawable.au),
        CountryModel("ES", "+34", "Spain", R.drawable.es),
        CountryModel("IT", "+39", "Italy", R.drawable.it),
        CountryModel("RU", "+7", "Russia", R.drawable.ru),
        CountryModel("MX", "+52", "Mexico", R.drawable.mx),
        CountryModel("ZA", "+27", "South Africa", R.drawable.za),
        CountryModel("TR", "+90", "Turkey", R.drawable.tr),
        CountryModel("NL", "+31", "Netherlands", R.drawable.nl),
        CountryModel("KR", "+82", "South Korea", R.drawable.kr),
        CountryModel("SA", "+966", "Saudi Arabia", R.drawable.sa)
    )
)