package com.csdev.designwaytest.mappers

abstract class BaseMapper<T, R> {
    abstract fun map(input: T): R
}