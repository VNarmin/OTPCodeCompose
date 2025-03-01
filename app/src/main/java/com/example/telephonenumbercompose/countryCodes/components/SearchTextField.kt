package com.example.telephonenumbercompose.countryCodes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telephonenumbercompose.ui.theme.GothamFontFamily

@Composable
fun SearchTextField(
    value : String,
    onValueChange : (String) -> Unit,
    focusManager : FocusManager
) {
    TextField(
        modifier = Modifier.padding(top = 16.dp, bottom = 24.dp).fillMaxWidth().height(56.dp)
            .background(color = Color(0xFFF1F1F1), shape = RoundedCornerShape(12.dp)),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = "Search",
                style = TextStyle(
                    color = Color(0xFFBCBCBC),
                    fontSize = 14.sp,
                    fontFamily = GothamFontFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start
                )
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = GothamFontFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color(0xFF3C3938),
            unfocusedTextColor = Color(0xFF3C3938),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color.LightGray,
            unfocusedPlaceholderColor = Color.Gray
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color(0xFF3C3938)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                tint = Color(0xFF3C3938)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}