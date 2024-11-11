package com.example.fifthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fifthapp.ui.LoginPage
import com.example.fifthapp.ui.RegisterPage
import com.example.fifthapp.ui.theme.FifthAppTheme
import com.google.firebase.FirebaseApp
//test
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            FifthAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login",
                ){
                    composable("login"){
                        LoginPage(goToRegisterPage ={
                            navController.navigate("register")
                        })
                    }
                    composable("register"){
                        RegisterPage(backToLoginPage = {
                            navController.navigate("login")
                        })
                    }
                }
            }
        }
    }
}

