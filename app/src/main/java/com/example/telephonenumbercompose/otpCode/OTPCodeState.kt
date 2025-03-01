package com.example.telephonenumbercompose.otpCode

data class OTPCodeState(
    // each digit of the OTP code can be null or an actual number
    val otpCode : List < Int? > = (1..4).map { null },
    val indexFocused : Int? = null,
    val inputValidity : Boolean? = null
)