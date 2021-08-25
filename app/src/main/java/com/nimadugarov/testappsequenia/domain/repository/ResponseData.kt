package com.nimadugarov.testappsequenia.domain.repository

sealed class ResponseData<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResponseData<T>(data)
    class Error<T>(message: String, data: T? = null) : ResponseData<T>(data, message)
}
