package com.itis.group11801.fedotova.smartfasting.data

data class ResultWrapper<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ResultWrapper<T> {
            return ResultWrapper(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ResultWrapper<T> {
            return ResultWrapper(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResultWrapper<T> {
            return ResultWrapper(Status.LOADING, data, null)
        }
    }
}
