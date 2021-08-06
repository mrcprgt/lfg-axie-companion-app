package com.mrcprgt.lfgaxiecompanionapp.tools.repository

import com.mrcprgt.lfgaxiecompanionapp.tools.LFGException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class RetrofitCall<T>(proxy: Call<T>) : CallDelegate<T, RetrofitResponse<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<RetrofitResponse<T>>) = proxy.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val requestMadeFor = call.request().url().toString()
            val code = response.code()
            val result = if (code in 200 until 300) {
                val body = response.body()!!
                RetrofitResponse.Success(body)
            } else {
                val message =
                    if (response.message().isNullOrEmpty()) "No message" else response.message()
                val errorBody = response.errorBody()
                    ?.string() ?: message
                RetrofitResponse.Failed(
                    LFGException("Failed request.")
                )
            }
            callback.onResponse(this@RetrofitCall, Response.success(result))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val requestMadeFor = call.request().url().toString()
            val result = if (t is IOException) {
                RetrofitResponse.Failed(
                    LFGException("Failed request.")
                )
            } else {
                RetrofitResponse.Failed(LFGException("Failed request."))
            }
            callback.onResponse(this@RetrofitCall, Response.success(result))
        }
    })

    override fun cloneImpl() = RetrofitCall(proxy.clone())
}
