package com.example.kotlinconcurrency

import kotlinx.coroutines.delay

class Worker(private val id: Int) {

    suspend fun performTask(taskName: String) {
        println("Worker $id is performing task: $taskName")
        delay(1000L) // Simulate long-running task
        println("Worker $id completed task: $taskName")
    }

    suspend fun fetchAndProcessData(fetcher: DataFetcher, processor: DataProcessor) {
        val data = fetcher.fetchData()
        processor.processData(data, id)
    }

    // Additional complex tasks
    suspend fun complexTask() {
        repeat(10) { i ->
            println("Worker $id is working on complex task part $i")
            delay(500L)
        }
        println("Worker $id completed complex task")
    }

    // Simulate error handling in coroutines
    suspend fun taskWithErrorHandling() {
        try {
            println("Worker $id started task with error handling")
            delay(500L)
            throw Exception("Simulated error in Worker $id")
        } catch (e: Exception) {
            println("Worker $id caught error: ${e.message}")
        }
    }
}