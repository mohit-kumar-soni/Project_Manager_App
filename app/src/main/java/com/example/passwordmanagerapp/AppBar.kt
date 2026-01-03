package com.example.passwordmanagerapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(title : String,
           onBackNavClicked: () -> Unit = {}){
    val navigationIcon : (@Composable () -> Unit)? =
        if (!title.contains("Password Manager")){{
            IconButton(onClick = {onBackNavClicked()}) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        } else{
            null
        }

    TopAppBar(title = { Text(text = title,
        color = colorResource(id = R.color.black),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 4.dp)
            .heightIn(max = 28.dp)) },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.white),
        navigationIcon = navigationIcon
        )
     }