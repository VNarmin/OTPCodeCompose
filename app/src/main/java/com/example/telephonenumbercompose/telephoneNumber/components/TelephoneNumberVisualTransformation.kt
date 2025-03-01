package com.example.telephonenumbercompose.telephoneNumber.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TelephoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text : AnnotatedString) : TransformedText {
        // formatting the phone number by chunking into groups (XX XXX XX XX)
        val formattedText = buildString {
            append(text.text.take(2))
            if (text.text.length > 2) append(" ${text.text.drop(2).take(3)}")
            if (text.text.length > 5) append(" ${text.text.drop(5).take(2)}")
            if (text.text.length > 7) append(" ${text.text.drop(7).take(2)}")
        }

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = TelephoneNumberOffsetMapping
        )
    }

    private object TelephoneNumberOffsetMapping : OffsetMapping {
        override fun originalToTransformed(offset : Int) : Int {
            var spaceCount = 0
            for (n in 0 until offset) {
                if (n > 0 && (n == 2 || n == 5 || n == 7)) spaceCount++
            }
            return offset + spaceCount
        }

        override fun transformedToOriginal(offset : Int) : Int {
            var spaceCount = 0
            for (n in 0 until offset) {
                if (n > 0 && (n == 2 || n == 6 || n == 9)) spaceCount++
            }
            return offset - spaceCount
        }
    }
}