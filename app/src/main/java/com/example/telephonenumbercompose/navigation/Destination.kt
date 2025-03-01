package com.example.telephonenumbercompose.navigation

import com.example.telephonenumbercompose.model.CountryModel
import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    data class TelephoneNumberRoute(val country : CountryModel) : Destination()

    @Serializable
    data object CountryCodesRoute : Destination()

    @Serializable
    data class OTPCodeRoute(val telephoneNumber : String) : Destination()
}