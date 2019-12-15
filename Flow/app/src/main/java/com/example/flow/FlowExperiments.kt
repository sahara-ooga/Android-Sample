package com.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun intStream(): Flow<Int> = flow {
    repeat(10) {
        delay(10)
        emit(it)
    }
}
