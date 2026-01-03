package com.example.passwordmanagerapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordmanagerapp.data.Password
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id : Long,
    viewModel : PasswordViewModel,
    navController: NavController
){
    val isEditMode = id != 0L

    val snackMessage = remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()
    if (id != 0L){
        val password = viewModel.getPasswordById(id)
            .collectAsState(initial = Password(0L,"","",""))
        viewModel.passwordTitleState = password.value.title
        viewModel.passwordEmailState = password.value.email
        viewModel.passwordPasswordState = password.value.password
    }else{
        viewModel.passwordTitleState =""
        viewModel.passwordEmailState =""
        viewModel.passwordPasswordState =""
    }

    Scaffold(modifier = Modifier.padding(top = 30.dp),
        scaffoldState = scaffoldState,
        topBar = {AppBar(title =

           if (id != 0L) stringResource(R.string.edit_password)
       else stringResource(R.string.add_password))
        {navController.navigateUp()}
        }
    )
    {
        Column(modifier = Modifier.padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Spacer(Modifier.height(10.dp))

            TextField(label = "Account Name", value = viewModel.passwordTitleState,
                onValueChanged = {viewModel.onPasswordTitleChanged(it)},

            )

            Spacer(Modifier.height(10.dp))

            TextField(
                label = "Email", value = viewModel.passwordEmailState,
                onValueChanged = {viewModel.onPasswordEmailChanged(it)},
                isPassword = isEditMode
            )

            Spacer(Modifier.height(10.dp))

            TextField(
                label = "Password", value = viewModel.passwordPasswordState,
                onValueChanged = {viewModel.onPasswordPasswordChanged(it) },
                isPassword = isEditMode
            )
            Spacer(Modifier.height(10.dp))

            Button(modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                if (viewModel.passwordTitleState.isNotEmpty()
                    && viewModel.passwordEmailState.isNotEmpty()
                    && viewModel.passwordPasswordState.isNotEmpty()){
                    if (id != 0L){
                        // Update Account
                        viewModel.updateAPassword(
                            Password(
                                id = id,
                                title = viewModel.passwordTitleState.trim(),
                                email = viewModel.passwordEmailState.trim(),
                                password = viewModel.passwordPasswordState.trim()
                            )

                        )
                    } else{
                        // ADD Account
                        viewModel.addAPassword(
                            Password(
                                title = viewModel.passwordTitleState.trim(),
                                email = viewModel.passwordEmailState.trim(),
                                password = viewModel.passwordPasswordState.trim()
                            )
                        )
                        snackMessage.value= " Account has been Created"

                    }
                }else{
                    snackMessage.value = "Enter field to Create a new Account"
                }
                scope.launch {
                    //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
            }) {
                Text(text = if (id != 0L) stringResource(R.string.edit_password)
                else stringResource(R.string.add_password),
                    style = TextStyle(fontSize = 24.sp))
            }

        }
    }
}
@Composable
fun TextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    isPassword: Boolean = false
){
    var passwordVisible by remember { mutableStateOf(true) }

      OutlinedTextField(value = value,
        onValueChange = { onValueChanged(it)},
        label ={ Text(text = label,color=Color.Black)},
        trailingIcon = {
            if (isPassword){
                IconButton(onClick = {
                    passwordVisible =!passwordVisible
                }) {
                    Icon(painter =if (passwordVisible)
                        painterResource(R.drawable.baseline_visibility_off_24)
                    else painterResource(id = R.drawable.baseline_visibility_24), contentDescription = null)
                }
            }
            },

        visualTransformation = if (isPassword && passwordVisible) PasswordVisualTransformation()
        else VisualTransformation.None,

        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 10.dp, end = 20.dp),

        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password
        else KeyboardType.Text
        ),

        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor =  colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),

            )
    )
}
