package com.lfgcompany.lfgaxiecompanionapp.tools.repository

import retrofit2.CallAdapter
import retrofit2.Converter

class ServiceConfiguration private constructor(
    private var converters: MutableSet<Converter.Factory>,
    private var callAdapters: MutableSet<CallAdapter.Factory>
) {

    fun getConverters(): List<Converter.Factory> = converters.toList()

    fun getCallAdapters(): List<CallAdapter.Factory> = callAdapters.toList()

    class Builder {
        private var converters: MutableSet<Converter.Factory> = mutableSetOf()
        private var callAdapters: MutableSet<CallAdapter.Factory> = mutableSetOf()

        fun addConverter(converter: Converter.Factory): Builder {
            this.converters.add(converter)
            return this
        }

        fun addCallAdapter(callAdapter: CallAdapter.Factory): Builder {
            this.callAdapters.add(callAdapter)
            return this
        }

        fun build(): ServiceConfiguration {
            return ServiceConfiguration(converters, callAdapters)
        }
    }
}
