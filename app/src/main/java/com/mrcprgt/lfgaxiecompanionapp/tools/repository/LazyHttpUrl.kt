package com.mrcprgt.lfgaxiecompanionapp.tools.repository

import okhttp3.HttpUrl

interface LazyHttpUrl {
    fun get() : HttpUrl
}
