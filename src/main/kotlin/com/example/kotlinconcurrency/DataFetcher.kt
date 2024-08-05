package com.example.kotlinconcurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DataFetcher {

    suspend fun fetchData(): List<String> = withContext(Dispatchers.IO) {
        val deferredData = (1..5).map {
            async {
                delay(500L) // Simulate network delay
                "Data $it"
            }
        }
        deferredData.awaitAll() // awaitAll() çağrısı ile asenkron olarak bekleniyor ve sonuçlar alınıyor
    }

    // Additional fetching logic
    suspend fun fetchFromMultipleSources(): List<String> = withContext(Dispatchers.IO) {
        val sources = listOf("Source 1", "Source 2", "Source 3")
        val deferredData = sources.map { source ->
            async {
                delay(700L) // Simulate network delay
                "Data from $source"
            }
        }
        deferredData.awaitAll()
    }
}