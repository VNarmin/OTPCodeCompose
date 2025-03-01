package com.example.telephonenumbercompose.telephoneNumber

interface TelephoneNumberIntent {
    data class OnTextChange(val inputValue : String) : TelephoneNumberIntent
    data class OnErrorChange(val errorState : Boolean) : TelephoneNumberIntent
}