package com.example.telephonenumbercompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.telephonenumbercompose.model.defaultCountryModel
import com.example.telephonenumbercompose.navigation.MainNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase : Context?) {
        newBase?.let { context ->
            val sp = context.getSharedPreferences("189", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("selected_country_name", defaultCountryModel.name)
            editor.apply()
            super.attachBaseContext(context)
        }
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainNavigation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() { }