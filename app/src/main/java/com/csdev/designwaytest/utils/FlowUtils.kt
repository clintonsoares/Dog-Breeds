package com.csdev.designwaytest.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

// temporarily set the value to the stateflow
suspend fun <T> MutableStateFlow<T?>.set(value: T, idle: T? = null, delay: Long = 10) {
    this.value = value
    delay(delay)
    this.value = idle
}