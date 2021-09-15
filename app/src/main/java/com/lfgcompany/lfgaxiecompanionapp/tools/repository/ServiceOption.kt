package com.lfgcompany.lfgaxiecompanionapp.tools.repository

import okhttp3.HttpUrl

class ServiceOption private constructor(
    private val httpUrl: LazyHttpUrl,
    private val client: Client?
) {

    fun getServiceHost(): LazyHttpUrl = httpUrl
    fun getClient(): Client? = client

    class Builder {
        private var httpUrl: LazyHttpUrl
        private var client: Client? = null

        constructor(httpUrl: LazyHttpUrl) {
            this.httpUrl = httpUrl
        }

        constructor(host: ServiceHost) {
            this.httpUrl = object : LazyHttpUrl {
                override fun get(): HttpUrl {
                    return HttpUrl.get(host.url)
                }
            }
        }

        fun setClient(client: Client): Builder {
            this.client = client
            return this
        }

        fun build(): ServiceOption {
            return ServiceOption(
                httpUrl = httpUrl,
                client = client
            )
        }
    }
}
