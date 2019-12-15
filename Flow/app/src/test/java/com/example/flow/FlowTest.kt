package com.example.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FlowTest {

    @Test
    fun intStreamTest() {
        runBlocking {
            val ints: Flow<Int> = intStream()

            ints.collect {
                println(it)
            }
        }
    }

    @Test
    fun intStreamTest2() {
        val ints = intStream()
        val processed = ints
            .filter { it % 2 == 0 }
            .map { it * 2 }
            .drop(1)
            .take(3)

        runBlocking {
            processed.collect {
                println("$it")
            }
        }

        runBlocking {
            val result = processed.first()
            println(result)
        }
    }
}
