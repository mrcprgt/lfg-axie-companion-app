package com.mrcprgt.lfgaxiecompanionapp.tools.repository

import com.mrcprgt.lfgaxiecompanionapp.tools.LFGException
import retrofit2.HttpException
import java.io.IOException

inline fun <Response> executeNow(block : () -> Response) : Response {
    try {
        return block()
    } catch (exception : Exception) {
        when (exception) {
            is IOException -> throw LFGException("JavaIOException")
            is HttpException -> {
                val code = exception.code()
                val errorBody = exception
                    .response()
                    ?.errorBody()
                    ?.string() ?: "No Error Body"
                throw LFGException("$code $errorBody")
            }
            else -> throw LFGException(exception.message ?: "Something went wrong.")
        }
    }
}

fun <T> RetrofitResponse<T>.process(): T {
    return when (this) {
        is RetrofitResponse.Success -> {
            if (this.data is List<*> && this.data.isEmpty()) {
                throw LFGException("Data is empty!")
            }
            this.data
        }
        is RetrofitResponse.Failed -> throw this.exception
    }
}
