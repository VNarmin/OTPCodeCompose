package com.example.telephonenumbercompose.otpCode

sealed interface OTPCodeIntent {
    // for updating the OTP input and checking its validity
    data class OnValueChange(val value : Int?, val index : Int) : OTPCodeIntent
    // for updating the focused OTP input field
    data class OnFocusChange(val index : Int) : OTPCodeIntent
    // for moving the focus back and deleting the previous digit
    data object OnDeletePressed : OTPCodeIntent
}