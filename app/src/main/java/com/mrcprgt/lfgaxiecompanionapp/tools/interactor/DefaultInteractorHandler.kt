package com.mrcprgt.lfgaxiecompanionapp.tools.interactor

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DefaultInteractorHandler @Inject constructor() : InteractorHandler {
    override fun getExceptionHandler(): InteractorHandler.ExceptionHandler {
        return object : InteractorHandler.ExceptionHandler {
            override suspend fun onError(callerName: String, exception: Exception): Exception {
                throw exception
            }
        }
    }

    override fun getDispatcher(): InteractorHandler.Dispatcher {
        return object : InteractorHandler.Dispatcher {
            override fun io(): CoroutineContext {
                return Dispatchers.IO
            }

            override fun ui(): CoroutineContext {
                return Dispatchers.Main
            }
        }
    }
}
