package com.example.telephonenumbercompose.telephoneNumber

data class TelephoneNumberState(
    val telephoneNumber : String = "",
    val telephoneNumberError : Boolean = false,
    val buttonEnabled : Boolean = false
)