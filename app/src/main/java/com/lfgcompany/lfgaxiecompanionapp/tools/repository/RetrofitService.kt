package com.lfgcompany.lfgaxiecompanionapp.tools.repository

import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitService(
    private val option: ServiceOption
) {
    private var converters: MutableSet<Converter.Factory> = mutableSetOf()
    private var callAdapters: MutableSet<CallAdapter.Factory> = mutableSetOf()

    fun addConverter(converter: Converter.Factory): RetrofitService {
        this.converters.add(converter)
        return this
    }

    fun addCallAdapter(converter: CallAdapter.Factory): RetrofitService {
        this.callAdapters.add(converter)
        return this
    }

    fun <T> create(
        serviceClass: Class<T>
    ): T {
        val builder = Retrofit.Builder()
            .baseUrl(option.getServiceHost().get())

        val client = option.getClient()
        if (client != null) {
            builder.client(client.get())
        }

        converters.forEach {
            builder.addConverterFactory(it)
        }
        callAdapters.forEach {
            builder.addCallAdapterFactory(it)
        }

        return builder.build().create(serviceClass)
    }
}
