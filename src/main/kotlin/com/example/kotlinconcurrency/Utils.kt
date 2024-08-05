package com.example.kotlinconcurrency

object Utils {

    fun printMessage(message: String) {
        println("Utils: $message")
    }

    // Additional utility functions
    fun calculateSum(a: Int, b: Int): Int {
        return a + b
    }

    fun formatData(data: String): String {
        return "Formatted: $data"
    }

    fun logError(error: String) {
        println("Error: $error")
    }
}