package com.example.composeproductlister_cleanmvvm.utils

/** The purpose of ResultWrapper is to manage the state of an asynchronous operation in three
 * states: SUCCESS, ERROR and LOADING  **/

data class ResultWrapper<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): ResultWrapper<T> =
            ResultWrapper(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ResultWrapper<T> =
            ResultWrapper(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ResultWrapper<T> =
            ResultWrapper(status = Status.LOADING, data = data, message = null)
    }
}