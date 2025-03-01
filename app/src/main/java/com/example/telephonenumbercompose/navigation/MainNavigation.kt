package com.example.telephonenumbercompose.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.telephonenumbercompose.countryCodes.CountryCodesScreen
import com.example.telephonenumbercompose.model.CountryModel
import com.example.telephonenumbercompose.model.defaultCountryModel
import com.example.telephonenumbercompose.otpCode.OTPCodeScreen
import com.example.telephonenumbercompose.telephoneNumber.TelephoneNumberScreen
import kotlin.reflect.typeOf

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Destination.TelephoneNumberRoute(country = defaultCountryModel)
    ) {
        composable < Destination.TelephoneNumberRoute > (
            typeMap = mapOf(typeOf < CountryModel > () to CustomNavType.CountryType)
        ) { backStackEntry ->
            val args : Destination.TelephoneNumberRoute = backStackEntry.toRoute()
            TelephoneNumberScreen(
                countryModel = args.country,
                onNavigateToCountryCodes = {
                    navController.safeNavigate(Destination.CountryCodesRoute)
                },
                onNavigateToOTPCode = { telephoneNumber ->
                    navController.safeNavigate(
                        Destination.OTPCodeRoute(telephoneNumber = telephoneNumber)
                    )
                }
            )
        }
        composable < Destination.CountryCodesRoute > {
            CountryCodesScreen(
                onNavigateBack = { countryModel ->
                    navController.safeNavigate(Destination.TelephoneNumberRoute(country = countryModel)) {
                        popUpTo(Destination.TelephoneNumberRoute(country = countryModel)) { inclusive = true }
                    }
                }
            )
        }
        composable < Destination.OTPCodeRoute > { backStackEntry ->
            val args : Destination.OTPCodeRoute = backStackEntry.toRoute()
            OTPCodeScreen(
                telephoneNumber = args.telephoneNumber,
                onNavigateBack = { navController.navigateBack() }
            )
        }
    }
}

fun NavController.safeNavigate(
    destinationRoute : Destination,
    builder : NavOptionsBuilder.() -> Unit = { }
) {
    if (this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        this.navigate(destinationRoute, builder)
    }
}

fun NavController.navigateBack() {
    if (this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        this.popBackStack()
    }
}