package com.example.telephonenumbercompose.otpCode

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val DEFAULT_OTP_CODE = "5555"

class OTPCodeViewModel : ViewModel() {
    private val _state = MutableStateFlow(OTPCodeState())
    val state = _state.asStateFlow()

    fun onAction(intent : OTPCodeIntent) {
        when (intent) {
            is OTPCodeIntent.OnFocusChange -> onFocusChange(indexFocused = intent.index)

            is OTPCodeIntent.OnValueChange -> {
                onValueChange(currentValue = intent.value, currentIndex = intent.index)
            }

            is OTPCodeIntent.OnDeletePressed -> getPreviousFocusedIndex()
        }
    }

    private fun onFocusChange(indexFocused : Int) {
        _state.update { state -> state.copy(indexFocused = indexFocused) }
    }

    // triggered when the user enters or deletes an OTP digit
    private fun onValueChange(currentValue : Int?, currentIndex : Int) {
        val newCode = _state.value.otpCode.mapIndexed { index, value ->
            if (index == currentIndex) currentValue
            else value
        }

        val deleteValue = currentValue == null

        _state.update { state -> state.copy(
            otpCode = newCode,
            indexFocused = if (deleteValue || state.otpCode.getOrNull(currentIndex) != null) {
                state.indexFocused
            } else {
                getNextFocusedIndex(
                    currentCode = state.otpCode,
                    currentFocusedIndex = state.indexFocused
                )
            },
            inputValidity = if (newCode.none { single -> single == null }) {
                // if all 4 OTP entries are filled, then compare the input with DEFAULT_OTP_CODE
                newCode.joinToString("") == DEFAULT_OTP_CODE
            } else null // if incomplete, set the input validity to null
        ) }
    }

    private fun getPreviousFocusedIndex() {
        val current = _state.value.indexFocused
        val previous = current?.minus(1)?.coerceAtLeast(0)
        _state.update { state -> state.copy(
            otpCode = state.otpCode.mapIndexed { index, value ->
                if(index == previous) null
                else value
            },
            indexFocused = previous
        ) }
    }

    private fun getNextFocusedIndex(
        currentCode : List < Int? >,
        currentFocusedIndex : Int?
    ) : Int? {
        if (currentFocusedIndex == null) return null

        if (currentFocusedIndex == 3) return currentFocusedIndex

        return getFirstEmptyEntryIndexAfterCurrentFocusedIndex(
            currentCode = currentCode,
            currentFocusedIndex = currentFocusedIndex
        )
    }

    private fun getFirstEmptyEntryIndexAfterCurrentFocusedIndex(
        currentCode : List < Int? >,
        currentFocusedIndex : Int
    ) : Int {
        currentCode.forEachIndexed { index, value ->
            if (index <= currentFocusedIndex) return@forEachIndexed
            if (value == null) return index
        }
        return currentFocusedIndex
    }
}