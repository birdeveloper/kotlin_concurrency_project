package com.example.kotlinconcurrency

import kotlinx.coroutines.delay

class DataProcessor {

    suspend fun processData(data: List<String>, workerId: Int) {
        for (item in data) {
            println("Worker $workerId is processing: $item")
            delay(200L) // Simulate processing delay
        }
        println("Worker $workerId completed processing.")
    }

    // Additional processing logic
    suspend fun processLargeDataSet(data: List<String>, workerId: Int) {
        data.chunked(5).forEach { chunk ->
            println("Worker $workerId is processing chunk: $chunk")
            delay(500L) // Simulate processing delay
        }
        println("Worker $workerId completed processing large data set.")
    }

    suspend fun processDataWithRetry(data: List<String>, workerId: Int) {
        for (item in data) {
            println("Worker $workerId is processing: $item")
            retry(3) {
                delay(200L) // Simulate processing delay
                if (item == "Data 3") throw Exception("Error processing $item")
            }
        }
        println("Worker $workerId completed processing with retry.")
    }

    private suspend fun retry(times: Int, block: suspend () -> Unit) {
        repeat(times) {
            try {
                block()
                return
            } catch (e: Exception) {
                println("Retry failed with exception: ${e.message}")
            }
        }
    }
}