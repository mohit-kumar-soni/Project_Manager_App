package com.example.passwordmanagerapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordmanagerapp.data.Password

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeActivity(navController: NavController,
                 viewModel: PasswordViewModel){
    Scaffold(modifier = Modifier.padding(top = 30.dp),
        topBar = {AppBar(title = "Password Manager")},

        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp).size(70.dp),
                contentColor = Color.White,
                backgroundColor = colorResource(R.color.skyBlue),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate(Screen.AddScreen.route + "/0L")

                }
            ) {
                Icon(imageVector = Icons.Default.Add,contentDescription = null, Modifier.size(50.dp))
            }
        }
    )

    {
        val passwordList = viewModel.getAllPassword.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier.fillMaxSize().padding(it)) {

            items(passwordList.value, key = {data-> data.id}){
                data ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                            viewModel.deleteAPassword(data)
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if (dismissState.dismissDirection
                                == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                            label = ""
                        )

                        val alignment=Alignment.CenterEnd
                        Box(modifier = Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ){
                            Icon(Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.White)

                        }
                    },
                    directions = setOf( DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(0.25f)},
                    dismissContent = {
                        PasswordItem(password = data){
                            val id = data.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PasswordItem(password: Password, onClick: () -> Unit){
    Column(modifier = Modifier.padding(top = 20.dp)) {
        Card(modifier = Modifier.fillMaxWidth().padding( start = 16.dp, end = 16.dp).size(70.dp)
            .clickable{},
            shape = RoundedCornerShape(30.dp),
            elevation = 4.dp,
            backgroundColor = Color.White) {
            Row(modifier = Modifier.padding(start = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = password.title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Spacer(Modifier.width(16.dp))

                Text(text = "*******", color = Color.Gray, fontSize = 28.sp)

                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = { onClick()},
                        modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_forward),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

        }
    }
}