package com.example.telephonenumbercompose.telephoneNumber

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.telephonenumbercompose.R
import com.example.telephonenumbercompose.model.CountryModel
import com.example.telephonenumbercompose.telephoneNumber.components.CountryCodeBox
import com.example.telephonenumbercompose.telephoneNumber.components.SocialAccountButton
import com.example.telephonenumbercompose.telephoneNumber.components.TelephoneNumberTextField
import com.example.telephonenumbercompose.ui.theme.GothamFontFamily
import com.example.telephonenumbercompose.ui.theme.MasifaFontFamily
import com.example.telephonenumbercompose.ui.theme.PoppinsFontFamily

@Composable
fun TelephoneNumberScreen(
    viewModel : TelephoneNumberViewModel = viewModel(),
    countryModel : CountryModel,
    onNavigateToCountryCodes : () -> Unit,
    onNavigateToOTPCode : (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.telephoneNumberError) {
        if (!state.telephoneNumberError) {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 48.dp, horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .height((LocalConfiguration.current.screenHeightDp * 0.45F).dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenHeader()
            MainContent(
                focusManager = focusManager,
                countryModel = countryModel,
                telephoneNumberValue = state.telephoneNumber,
                telephoneNumberError = state.telephoneNumberError,
                onValueChange = { newValue ->
                    viewModel.onAction(TelephoneNumberIntent.OnTextChange(inputValue = newValue))
                },
                onNavigateToCountryCodes = onNavigateToCountryCodes
            )
        }
        ScreenFooter(
            buttonEnabled = state.buttonEnabled,
            countryCode = countryModel.dialCode,
            telephoneNumber = state.telephoneNumber,
            onNavigateToOTPCode = onNavigateToOTPCode
        )
    }
}

@Composable
fun ScreenHeader() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow_left),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            text = "Nömrənizi və ya sosial hesabınızı daxil edin",
            style = TextStyle(
                color = Color(0xFF3C3938),
                fontFamily = MasifaFontFamily,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        )
    }
}

@Composable
fun MainContent(
    focusManager : FocusManager,
    telephoneNumberValue : String,
    telephoneNumberError : Boolean,
    onValueChange : (String) -> Unit,
    countryModel : CountryModel,
    onNavigateToCountryCodes : () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CountryCodeBox(
                modifier = Modifier.fillMaxHeight().weight(1F),
                countryModel = countryModel,
                onNavigateToCountryCodes = onNavigateToCountryCodes
            )
            TelephoneNumberTextField(
                modifier = Modifier.fillMaxHeight().weight(3F),
                value = telephoneNumberValue,
                onValueChange = onValueChange,
                error = telephoneNumberError,
                focusManager = focusManager
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "və ya",
            style = TextStyle(
                color = Color(0xFF1D1D1D),
                fontSize = 16.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        )
        SocialAccountButton(modifier = Modifier.fillMaxWidth().height(56.dp))
    }
}

@Composable
fun ScreenFooter(
    buttonEnabled : Boolean,
    countryCode : String,
    telephoneNumber : String,
    onNavigateToOTPCode : (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                append("Növbəti düyməsinə toxunmaqla siz\n")

                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF3C3938),
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append("İstifadə Şərtlərini")
                }

                append(" və ")

                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF3C3938),
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append("Gizlilik Siyasətini")
                }

                append("\noxuduğunuzu və razılaşdığınızı təsdiq edirsiniz.")
            },
            style = TextStyle(
                color = Color(0xFF848484),
                fontSize = 13.sp,
                fontFamily = GothamFontFamily,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        )
        Button(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            enabled = buttonEnabled,
            shape = RoundedCornerShape(5.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (buttonEnabled) Color(0xFFFBE502) else Color(0xFFF1F1F1),
                contentColor = Color.Unspecified
            ),
            onClick = {
                val fullTelephoneNumber = "$countryCode " +
                        "${telephoneNumber.substring(0, 2)} " +
                        "${telephoneNumber.substring(2, 5)} " +
                        "${telephoneNumber.substring(5, 7)} " +
                        telephoneNumber.substring(7, 9)
                onNavigateToOTPCode(fullTelephoneNumber)
            }
        ) {
            Text(
                text = "Növbəti",
                style = TextStyle(
                    color = if (buttonEnabled) Color(0xFF14110F) else Color(0xFF878787),
                    fontSize = 16.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}