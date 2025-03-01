package com.example.telephonenumbercompose.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.telephonenumbercompose.model.CountryModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val CountryType = object : NavType < CountryModel >  (
        isNullableAllowed = false
    ) {
        override fun get(bundle : Bundle, key : String) : CountryModel? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value : String) : CountryModel {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun put(bundle : Bundle, key : String, value : CountryModel) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun serializeAsValue(value : CountryModel) : String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}