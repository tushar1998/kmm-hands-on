package com.example.todoapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.Greeting
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    var text by remember {
                        mutableStateOf("Loading")
                    }

                    var roadster by remember {
                        mutableStateOf("Loading Roadsters...");
                    }

//                    LaunchedEffect(true) {
//                        scope.launch {
//                            text = try {
//                                println("--------Hello World---------")
//                                Greeting().greetings()
//                            } catch (e: Exception) {
//                                e.localizedMessage ?: "error"
//                            }
//                        }
//                    }

                    LaunchedEffect(true) {
                        scope.launch {
                            val response = Greeting().getAllRoadster()
                            val roadsterDetails: String? = response?.name

                            println("Roadster Details $roadsterDetails")

                                if(roadsterDetails != null){

                                    roadster = roadsterDetails
                                }
                        }
                    }

                    GreetingView(roadster)
                    GreetingView(Greeting().helloSDK())
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
