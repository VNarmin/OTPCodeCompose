package com.example.telephonenumbercompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.telephonenumbercompose.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val MasifaFontFamily = FontFamily(
    fonts = listOf(
        Font(R.font.masifa_regular, FontWeight.Normal),
        Font(R.font.masifa_light, FontWeight.Light),
        Font(R.font.masifa_medium, FontWeight.Medium),
        Font(R.font.masifa_bold, FontWeight.Bold)
    )
)

val PoppinsFontFamily = FontFamily(
    fonts = listOf(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_bold, FontWeight.Bold)
    )
)

val GothamFontFamily = FontFamily(
    fonts = listOf(
        Font(R.font.gotham_light, FontWeight.Light),
        Font(R.font.gotham_medium, FontWeight.Medium),
        Font(R.font.gotham_bold, FontWeight.Bold)
    )
)