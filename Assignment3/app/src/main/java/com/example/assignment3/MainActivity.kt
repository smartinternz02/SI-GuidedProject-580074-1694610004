package com.example.assignment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment3.ui.theme.Assignment3Theme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx. compose. foundation. text. KeyboardActions
import androidx. compose. foundation. text. KeyboardOptions
import androidx. compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text. input. ImeAction
import androidx.compose.ui. text.input. KeyboardType
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.net.Uri
import android.content.ActivityNotFoundException
import android. content. Context
import androidx.compose.ui.platform. LocalContext
import androidx.compose.ui.res .painterResource
import androidx.compose.ui. semantics. Role. Companion. Image
import androidx. compose. foundation. Image
import kotlinx.coroutines. flow.collect
import android.widget. Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    var selectedWebsite by remember { mutableStateOf("") }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val webistes = listOf(
        "https://www.amazon.in",
        "https://www.apple.com/in",
        "https://gmail.com",
        "https://www.github.com"
    )
    Box(
        modifier = Modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Login Image",
                modifier = Modifier
                    .size(150.dp) 
                    .padding(16.dp)
            )

            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge.copy(color = Color(0xFF476810))
            )
            // Username Text field
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding()
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Password Text field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        //Handle Login here
                    }),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding()
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Dropdown Menu
            Text(text="List of Websites")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .clickable(onClick = { isMenuExpanded = true })
            ) {
                Text(text = selectedWebsite)
                DropdownMenu(
                    expanded = isMenuExpanded ,
                    onDismissRequest = { isMenuExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    webistes.forEach { website ->
                        DropdownMenuItem(
                            text = { Text(text = website)},
                            onClick = {
                                selectedWebsite = website
                                isMenuExpanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            //Login Button
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedWebsite))

                    //Check if there's an activity that can handle the intent
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    } else {
                        //Handle the case where there is no web browser available
                        Toast.makeText(context, "No web browser available", Toast.LENGTH_LONG)
                            .show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Open Website")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}