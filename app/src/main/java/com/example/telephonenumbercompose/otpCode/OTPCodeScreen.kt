package com.example.telephonenumbercompose.otpCode

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.telephonenumbercompose.R
import com.example.telephonenumbercompose.ui.theme.MasifaFontFamily
import com.example.telephonenumbercompose.ui.theme.PoppinsFontFamily

@Composable
fun OTPCodeScreen(
    viewModel : OTPCodeViewModel = viewModel(),
    telephoneNumber : String,
    onNavigateBack : () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequesters = remember { List(4) { FocusRequester() } }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.indexFocused) {
        state.indexFocused?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(state.otpCode) {
        val fullyEntered = state.otpCode.none { single -> single == null }
        if (fullyEntered) {
            focusRequesters.forEach { focusRequester ->  focusRequester.freeFocus() }
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 48.dp, horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        ScreenHeader(onNavigateBack = onNavigateBack)
        Text(
            text = "4 rəqəmli kod\n$telephoneNumber nömrəsinə göndərildi",
            style = TextStyle(
                color = Color(0xFF737373),
                fontFamily = PoppinsFontFamily,
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height((LocalConfiguration.current.screenHeightDp * 0.05F).dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.otpCode.forEachIndexed { index, value ->
                SingleOTPCodeEntry(
                    value = value,
                    onValueChange = { newValue ->
                        viewModel.onAction(OTPCodeIntent.OnValueChange(value = newValue, index = index))
                    },
                    onFocusChanged = { focused ->
                        if (focused) viewModel.onAction(OTPCodeIntent.OnFocusChange(index = index))
                    },
                    onDeletePressed = {
                        viewModel.onAction(OTPCodeIntent.OnDeletePressed)
                    },
                    focusManager = focusManager,
                    focusRequester = focusRequesters[index],
                    validOTPCode = state.inputValidity
                )
            }
        }
    }
}

@Composable
fun ScreenHeader(onNavigateBack : () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            modifier = Modifier.clip(CircleShape)
                .clickable { onNavigateBack() },
            painter = painterResource(R.drawable.arrow_left),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            text = "Telefon nömrənizi təsdiqləyin",
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
fun SingleOTPCodeEntry(
    value : Int?,
    onValueChange : (Int?) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onDeletePressed : () -> Unit,
    focusManager : FocusManager,
    focusRequester : FocusRequester,
    validOTPCode : Boolean?
) {
    val textFieldValue = remember(value) {
        mutableStateOf(
            TextFieldValue(
                text = value?.toString().orEmpty(),
                selection = TextRange(
                    index = if (value != null) 1 else 0
                )
            )
        )
    }

    var focused by remember { mutableStateOf(false) }

    val borderColor = when (validOTPCode) {
        true -> Color.Green
        false -> Color.Red
        null -> Color.Transparent
    }

    TextField(
        modifier = Modifier.size(72.dp)
            .background(shape = RoundedCornerShape(12.dp), color = Color(0xFFF1F1F1))
            .border(
                width = 1.dp,
                color = if (focused) Color(0xFF3C3938) else borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .focusRequester(focusRequester = focusRequester)
            .onFocusChanged { focusState ->
                focused = focusState.isFocused
                onFocusChanged(focused)
            }
            .onKeyEvent { event ->
                val deletePressed = event.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DEL
                if (deletePressed && value == null) {
                    onDeletePressed()
                }
                false
            },
        value = textFieldValue.value.text,
        onValueChange = { newText ->
            if (newText.length <= 1 && newText.isDigitsOnly()) {
                onValueChange(newText.toIntOrNull())
            }
        },
        textStyle = TextStyle(
            fontSize = 44.sp,
            fontFamily = MasifaFontFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color(0xFF3C3938),
            unfocusedTextColor = Color(0xFF3C3938),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color(0xFF3C3938)
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}