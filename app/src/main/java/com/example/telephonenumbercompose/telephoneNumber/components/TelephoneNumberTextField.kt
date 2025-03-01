package com.example.telephonenumbercompose.telephoneNumber.components

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telephonenumbercompose.ui.theme.PoppinsFontFamily

@Composable
fun TelephoneNumberTextField(
    modifier : Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    error : Boolean,
    focusManager : FocusManager
) {
    var focused by remember { mutableStateOf(false) }

    TextField(
        modifier = modifier
            .focusable()
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = if (focused) Color(0xFFFDF497) else Color(0xFFBCBCBC),
                shape = RoundedCornerShape(12.dp)
            )
            .onFocusChanged { focusState -> focused = focusState.isFocused },
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = "XX XXX XX XX",
                style = TextStyle(
                    color = Color(0xFFBCBCBC),
                    fontSize = 16.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start
                )
            )
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedTextColor = Color(0xFF2A2A2A),
            unfocusedTextColor = Color(0xFFBCBCBC),
            errorTextColor = Color.Red,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        isError = error,
        singleLine = true,
        visualTransformation = TelephoneNumberVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}