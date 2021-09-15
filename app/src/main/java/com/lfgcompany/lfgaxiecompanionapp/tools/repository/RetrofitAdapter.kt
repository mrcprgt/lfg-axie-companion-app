package com.lfgcompany.lfgaxiecompanionapp.tools.repository

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RetrofitAdapter (
    private val responseType : Type
) : CallAdapter<Type, Call<RetrofitResponse<Type>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<Type>): Call<RetrofitResponse<Type>> {
        return RetrofitCall(call)
    }

}
