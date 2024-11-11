package com.example.fifthapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fifthapp.ui.theme.FifthAppTheme
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    goToRegisterPage:() -> Unit
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isLoggedIn by rememberSaveable { mutableStateOf(false) }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Login Page")
                },
                actions = {
                    IconButton(onClick = {
                        logout()
                        isLoggedIn = false
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "logout")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it},
                label = {
                    Text("E-mail")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text("Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                modifier = Modifier.align(Alignment.End),
                onClick = goToRegisterPage
            ) {
                Text(text = "Register your email")
            }
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    loginUser(email, password)
                    isLoggedIn = true
                }
            ) {
                Text(text = "Login")
            }

            if (isLoggedIn) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                if (currentUser != null) {
                    currentUser.email?.let {
                        Text(
                            text = it,
                            style = typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}

fun loginUser(email: String, password: String){
    val auth = FirebaseAuth.getInstance()

    auth.signInWithEmailAndPassword(email, password)
}

fun logout(){
    val auth = FirebaseAuth.getInstance()

    auth.signOut()
}