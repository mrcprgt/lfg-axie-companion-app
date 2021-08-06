package com.mrcprgt.lfgaxiecompanionapp.tools.repository

import okhttp3.OkHttpClient

interface Client {
    fun get() : OkHttpClient
}
