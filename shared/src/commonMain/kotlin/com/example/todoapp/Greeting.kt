package com.example.todoapp

import com.apollographql.apollo3.api.ApolloResponse
import kotlin.random.Random

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*


//@Serializable
//data class RoadsterData(val data: GetAllRoadstersQuery.Data?)
//
//@Serializable
//data class RoadsterContainer(val roadster: RoadsterDetails)
//
//@Serializable
//data class RoadsterDetails(
//    val name: String,
//    val details: String,
//)

class Greeting {
    private val platform: Platform = getPlatform()
    private val client = HttpClient()

    fun greet(): String {
        val firstWord = if (Random.nextBoolean()) "Hi!" else "Hello!"

        return "$firstWord Guess what this is! > ${platform.name}!"
    }

    suspend fun greetings(): String {
        val response = client.get("https://jsonplaceholder.typicode.com/todos")
        return response.body();
    }

    suspend fun getAllRoadster():
            GetAllRoadstersQuery.Roadster? {
        val apolloClient = createApolloClient();
        val response = apolloClient.query(GetAllRoadstersQuery()).execute()

        return response.data?.roadster
    }
}