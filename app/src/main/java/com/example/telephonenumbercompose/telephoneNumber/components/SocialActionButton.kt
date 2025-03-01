package com.example.telephonenumbercompose.telephoneNumber.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telephonenumbercompose.R
import com.example.telephonenumbercompose.ui.theme.PoppinsFontFamily

@Composable
fun SocialAccountButton(modifier : Modifier) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(width = 1.dp, color = Color(0xFFBCBCBC)),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified
        ),
        onClick = { }
    ) {
        Box (modifier = Modifier.fillMaxSize()) {
            Icon(
                modifier = Modifier.align(Alignment.CenterStart),
                painter = painterResource(R.drawable.google_icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Google ilə davam et",
                style = TextStyle(
                    color = Color(0xFF9B9B9B),
                    fontSize = 16.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}