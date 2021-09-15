package com.lfgcompany.lfgaxiecompanionapp.tools.interactor

import kotlinx.coroutines.*

abstract class Interactor <out Type, in Params> (
    private val handler : InteractorHandler
) where Type : Any {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(
        handler.getDispatcher().io() + job
    )

    abstract suspend fun run(params: Params): Type

    fun execute(
        params : Params,
        onResult : (InteractorResult<Type, Exception>) -> Unit
    ) {
        scope.launch {
            val data = scope.async {
                run(params)
            }
            withContext(handler.getDispatcher().ui()) {
                try {
                    onResult(InteractorResult.Success(data.await()))
                } catch (e: Exception) {
                    throw handler.getExceptionHandler().onError(getSubclassName(), e)
                }
            }
        }
    }

    fun cancel() {
        scope.coroutineContext.cancelChildren()
    }

    suspend fun execute(params: Params): Type = withContext(
        scope.coroutineContext + handler.getDispatcher().io()
    ) {
        try {
            run(params)
        }catch (e : java.lang.Exception) {
            throw handler.getExceptionHandler().onError(getSubclassName(),e)
        }
    }

    private fun getSubclassName() : String {
        return this.javaClass.asSubclass(this.javaClass).simpleName
    }
}
