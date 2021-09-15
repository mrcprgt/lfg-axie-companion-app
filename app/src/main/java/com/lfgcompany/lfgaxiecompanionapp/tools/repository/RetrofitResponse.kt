package com.lfgcompany.lfgaxiecompanionapp.tools.repository

import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException

sealed class RetrofitResponse<out T> {
    data class Success<T>(val data : T) : RetrofitResponse<T>()
    data class Failed(val exception: LFGException) : RetrofitResponse<Nothing>()
}
