package com.example.telephonenumbercompose.countryCodes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telephonenumbercompose.model.CountryModel
import com.example.telephonenumbercompose.ui.theme.GothamFontFamily

@Composable
fun CountryEntry(
    countryModel : CountryModel,
    onClicked : (CountryModel) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { onClicked(countryModel) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
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
            Text(
                text = countryModel.name,
                style = TextStyle(
                    color = Color(0xFF3C3938),
                    fontFamily = GothamFontFamily,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                )
            )
        }
        Text(
            text = countryModel.dialCode,
            style = TextStyle(
                color = Color.Gray,
                fontFamily = GothamFontFamily,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.End
            )
        )
    }
}