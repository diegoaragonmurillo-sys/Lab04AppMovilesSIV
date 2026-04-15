package com.example.moviecounter

import android.R.attr.shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                MovieCounter()
            }
        }
    }
}

@Composable
fun MovieCounter() {
    var count by rememberSaveable { mutableStateOf(0) }
    var movieName by remember { mutableStateOf("") }
    var isWatched by remember { mutableStateOf(false) }
    var isRelease by remember { mutableStateOf(false) }


    Scaffold(
        floatingActionButton = {

            FloatingActionButton(onClick = {
                count = 0
                movieName = ""
            }, shape = RoundedCornerShape(16.dp)) {

                Text("RESET")z
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "Total películas: $count",
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            TextField(
                value = movieName,
                onValueChange = { movieName = it },
                label = { Text("Nombre de la película") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("¿Es un estreno?")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = isRelease, onCheckedChange = { isRelease = it })
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isWatched, onCheckedChange = { isWatched = it })
                Text("¿Ya la viste?")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (movieName.isNotBlank()) {
                        count++
                        movieName = ""
                        isWatched = false
                        isRelease = false
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Añadir Película")
            }
        }
    }
}