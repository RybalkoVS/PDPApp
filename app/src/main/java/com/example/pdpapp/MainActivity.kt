package com.example.pdpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pdpapp.di.AppComponent
import com.example.pdpapp.ui.theme.PDPAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        AppComponent.create().apply {
            inject(this@MainActivity)
        }

        setContent {
            PDPAppTheme {
            }
        }
    }
}