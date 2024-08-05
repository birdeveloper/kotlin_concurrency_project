package com.example.kotlinconcurrency

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Starting the concurrency example project...")
    val fetcher = DataFetcher()
    val processor = DataProcessor()
    val time = measureTimeMillis {

        val data = fetcher.fetchData()

        val job1 = launch { processor.processData(data, 1) }
        val job2 = launch { processor.processData(data, 2) }
        val job3 = launch { processor.processData(data, 3) }

        joinAll(job1, job2, job3)
    }
    println("Completed in $time ms")

    // Additional concurrency examples
    val worker1 = Worker(1)
    val worker2 = Worker(2)

    val job4 = launch { worker1.performTask("Task A") }
    val job5 = launch { worker2.performTask("Task B") }
    val job6 = launch { worker1.fetchAndProcessData(fetcher, processor) }
    val job7 = launch { worker2.fetchAndProcessData(fetcher, processor) }

    joinAll(job4, job5, job6, job7)

    // More examples with different coroutines usage
    runMultipleCoroutines()
}

suspend fun runMultipleCoroutines() {
    val jobs = List(100) { index ->
        GlobalScope.launch {
            delay(1000L)
            println("Coroutine $index is done")
        }
    }
    jobs.forEach { it.join() }
}