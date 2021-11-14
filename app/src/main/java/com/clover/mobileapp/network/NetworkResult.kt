package com.clover.mobileapp.network

/*
* Seale class for API Result
* */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    // For API Success
    class Success<T>(data: T) : NetworkResult<T>(data)

    // For API Error
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)

    // For API Call In Progress
    class Loading<T> : NetworkResult<T>()

}
