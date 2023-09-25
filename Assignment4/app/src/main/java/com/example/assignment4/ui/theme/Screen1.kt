package com.example.assignment4.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

data class Hotel(val title: String, val poster: Int)

val hotels = listOf(
    Hotel(),
    Hotel(),
    Hotel(),
    Hotel(),
    Hotel()
)

@Composable
fun HotelList() {
    LazyColumn {
        items(hotels) { hotel ->
            HotelItem(hotel = hotel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelItem(hotel: Hotel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(),
        onClick = {
            //Handle Movie Item Check
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = hotel.poster),
                contentDescription = hotel.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(hotel.title, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SearchBar(onQueryChange: (String) -> Unit) {
    var query by remember { mutableStateOf("") }

    BasicTextField(
        value = query,
        onValueChange = {
            query = it
            onQueryChange(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                //Handle Search Action
            }
        ),
        modifier = Modifier.run {
            fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        }
    )
}

@Composable
fun MovieRecommendationApp() {
    var filteredMovies by remember { mutableStateOf(movies) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar { query ->
            filteredMovies = if (query.isEmpty()) {
                movies
            } else {
                movies.filter { it.title.contains(query, ignoreCase = true)}
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        MovieList()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieRecommendationTheme {
                MovieRecommendationApp()
            }
        }
    }
}