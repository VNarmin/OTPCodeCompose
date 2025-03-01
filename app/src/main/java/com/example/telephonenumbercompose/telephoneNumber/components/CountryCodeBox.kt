package com.example.telephonenumbercompose.telephoneNumber.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.telephonenumbercompose.R
import com.example.telephonenumbercompose.model.CountryModel

@Composable
fun CountryCodeBox(
    modifier : Modifier,
    countryModel : CountryModel,
    onNavigateToCountryCodes : () -> Unit
) {
    var focused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = if (focused) Color(0xFFFDF497) else Color(0xFFBCBCBC),
                shape = RoundedCornerShape(12.dp)
            ).clickable(
                indication = null,
                interactionSource = null
            ) {
                focused = true
                onNavigateToCountryCodes()
              },
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.width(32.dp).height(20.dp),
                shape = RoundedCornerShape(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Unspecified
                )
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(countryModel.flagDrawableID),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Icon(
                painter = painterResource(R.drawable.arrow_down),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }
    }
}