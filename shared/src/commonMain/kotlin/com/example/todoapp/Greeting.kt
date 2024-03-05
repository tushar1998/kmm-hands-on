package com.example.todoapp

import kotlin.random.Random

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import com.example.kdfep.Greeting

class Greeting {
    private val platform: Platform = getPlatform()
    private val client = HttpClient()

    fun greet(): String {
        val firstWord = if (Random.nextBoolean()) "Hi!" else "Hello!"

        return "$firstWord Guess what this is! > ${platform.name}!"
    }

    suspend fun greetings(): String {
        val response = client.get("https://jsonplaceholder.typicode.com/todos")
        return response.body()
    }

    suspend fun getAllRoadster():
            GetAllRoadstersQuery.Roadster? {
        val apolloClient = createApolloClient()
        val response = apolloClient.query(GetAllRoadstersQuery()).execute()

        return response.data?.roadster
    }

    fun helloSDK(): String {
        val hello = com.example.kdfep.Greeting().greet()

        return hello
    }
}