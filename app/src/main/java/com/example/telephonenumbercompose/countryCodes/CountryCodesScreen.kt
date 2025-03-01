package com.example.telephonenumbercompose.countryCodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.telephonenumbercompose.R
import com.example.telephonenumbercompose.countryCodes.components.CountryEntry
import com.example.telephonenumbercompose.countryCodes.components.SearchTextField
import com.example.telephonenumbercompose.model.CountryModel
import com.example.telephonenumbercompose.ui.theme.MasifaFontFamily

@Composable
fun CountryCodesScreen(
    viewModel : CountryCodesViewModel = hiltViewModel < CountryCodesViewModel > (),
    onNavigateBack : (CountryModel) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val state by viewModel.state.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        modifier = Modifier.padding(horizontal = 12.dp)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        topBar = {
            ScreenHeader(
                selectedCountryModel = state.countryModel,
                onNavigateBack = onNavigateBack
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    SearchTextField(
                        value = searchQuery,
                        onValueChange = { query ->
                            viewModel.onAction(CountryCodesIntent.OnSearchQueryChange(searchQuery = query))
                        },
                        focusManager = focusManager)
                }
                items(state.countryList.size) { index ->
                    CountryEntry(
                        countryModel = state.countryList[index],
                        onClicked = {
                            val selected = state.countryList[index]
                            viewModel.onAction(CountryCodesIntent.OnCountryCodeSelected(countryModel = selected))
                            onNavigateBack(selected)
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        thickness = 1.dp,
                        color = Color(0xFFBCBCBC)
                    )
                }
            }
        }
    )
}

@Composable
fun ScreenHeader(
    selectedCountryModel : CountryModel,
    onNavigateBack : (CountryModel) -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 32.dp)) {
        Icon(
            modifier = Modifier.clip(CircleShape)
                .align(Alignment.CenterStart)
                .clickable { onNavigateBack(selectedCountryModel) },
            painter = painterResource(R.drawable.arrow_left),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Ölkə seçin",
            style = TextStyle(
                color = Color(0xFF3C3938),
                fontFamily = MasifaFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
    }
}