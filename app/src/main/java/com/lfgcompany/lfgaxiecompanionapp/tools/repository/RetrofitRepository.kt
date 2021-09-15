package com.lfgcompany.lfgaxiecompanionapp.tools.repository

import retrofit2.CallAdapter
import retrofit2.Converter

open class Repository {
    @Volatile
    protected var services: HashMap<String, Any?> = hashMapOf()

    protected inline fun <reified ServiceClass> provideService(
        key: String,
        option: ServiceOption,
        callAdapterFactory: CallAdapter.Factory = RetrofitCallAdapterFactory(),
        block: () -> Converter.Factory
    ): ServiceClass {
        if (!services.containsKey(key)) {
            val service = RetrofitService(option)
                .addCallAdapter(callAdapterFactory)
                .addConverter(block.invoke())
                .create(ServiceClass::class.java)
            services[key] = service
            return service
        }
        if (services[key] == null) throw IllegalStateException("Oops, service is null")
        return services[key] as ServiceClass
    }

    protected inline fun <reified ServiceClass> provideService(
        key: String,
        option: ServiceOption,
        callAdapterFactory: CallAdapter.Factory = RetrofitCallAdapterFactory(),
        converters: List<Converter.Factory>
    ): ServiceClass {
        if (!services.containsKey(key)) {
            val configs = ServiceConfiguration.Builder()
                .addCallAdapter(callAdapterFactory)
            converters.forEach {
                configs.addConverter(it)
            }
            val service = RetrofitService(option)
                .create(ServiceClass::class.java)
            services[key] = service
            return service
        }
        if (services[key] == null) throw IllegalStateException("Oops, service is null")
        return services[key] as ServiceClass
    }

    fun getActiveCountOfServices() = services.size
}
