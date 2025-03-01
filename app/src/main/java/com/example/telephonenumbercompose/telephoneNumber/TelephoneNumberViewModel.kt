package com.example.telephonenumbercompose.telephoneNumber

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TelephoneNumberViewModel : ViewModel() {
    private var _state = MutableStateFlow(TelephoneNumberState())
    val state = _state.asStateFlow()

    fun onAction(intent : TelephoneNumberIntent) {
        when (intent) {
            is TelephoneNumberIntent.OnTextChange -> {
                onTextChange(inputValue = intent.inputValue)
            }
            is TelephoneNumberIntent.OnErrorChange -> {
                onErrorChange(errorState = intent.errorState)
            }
        }
    }

    private fun onTextChange(inputValue : String) {
        if (inputValue.length < 10) {
            val error = !validateTelephoneNumber(inputValue)
            val enableButton = validateTelephoneNumber(inputValue)
            _state.update { state ->
                state.copy(
                    telephoneNumber = inputValue,
                    telephoneNumberError = error,
                    buttonEnabled = enableButton
                )
            }
        }
    }

    private fun onErrorChange(errorState : Boolean) {
        _state.update { state -> state.copy(telephoneNumberError = errorState) }
    }
}

private fun validateTelephoneNumber(inputValue : String) : Boolean {
    val digitsOnly = inputValue.filter { input -> input.isDigit() }
    return digitsOnly.length == 9
}