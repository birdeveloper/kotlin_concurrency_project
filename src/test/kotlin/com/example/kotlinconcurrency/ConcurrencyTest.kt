package com.example.kotlinconcurrency

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class ConcurrencyTest {

    @Test
    fun testDataFetcher() = runBlocking {
        val fetcher = DataFetcher()
        val data = fetcher.fetchData()
        assertTrue(data.isNotEmpty())
    }

    @Test
    fun testDataProcessor() = runBlocking {
        val processor = DataProcessor()
        val data = listOf("Data 1", "Data 2", "Data 3")
        processor.processData(data, 1)
        assertTrue(true)
    }
}