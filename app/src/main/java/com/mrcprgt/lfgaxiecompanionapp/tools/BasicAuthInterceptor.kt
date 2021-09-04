package com.mrcprgt.lfgaxiecompanionapp.tools

import android.content.Context
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BasicAuthInterceptor @Inject constructor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val credentials = Credentials.basic(
            "LFGTeam" ,
            "3Mc(M~:LR+PY7csw"
        )
        request = request.newBuilder()
            .addHeader("Authorization", credentials)
            .build()

        return chain.proceed(request)
    }

}