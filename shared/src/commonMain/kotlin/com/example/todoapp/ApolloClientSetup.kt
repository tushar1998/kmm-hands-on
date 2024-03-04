package com.example.todoapp

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.HttpNetworkTransport

// Create a client
fun createApolloClient(): ApolloClient {
    return ApolloClient.Builder().serverUrl("https://spacex-production.up.railway.app/").build()
}