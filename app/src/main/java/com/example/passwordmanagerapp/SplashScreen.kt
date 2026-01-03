package com.example.passwordmanagerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    LaunchedEffect(key1 = Unit) {
        delay(5000L)
        navController.navigate("home_screen") {
            popUpTo("splash_screen") {
                inclusive = true
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(id = R.drawable.icon),contentDescription = null,
            modifier = Modifier.size(150.dp))

        Spacer(Modifier.height(10.dp))
        Text(text = "Password Manager App",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(10.dp))
        Text(text = "All your passwords",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold)
        Text(text = "safe and secure in one app.",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold)
    }

}