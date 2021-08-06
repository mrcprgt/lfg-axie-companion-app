package com.mrcprgt.lfgaxiecompanionapp.tools.interactor

import kotlin.coroutines.CoroutineContext

interface InteractorHandler {

    interface Dispatcher {
        fun io () : CoroutineContext
        fun ui () : CoroutineContext
    }

    interface ExceptionHandler {
        suspend fun onError(callerName : String, exception : Exception) : Exception
    }

    fun getExceptionHandler() : ExceptionHandler

    fun getDispatcher() : Dispatcher
}
