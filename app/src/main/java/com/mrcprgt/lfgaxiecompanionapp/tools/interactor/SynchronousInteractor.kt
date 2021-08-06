package com.mrcprgt.lfgaxiecompanionapp.tools.interactor

abstract class SynchronousInteractor<Response, Param> {
    abstract fun execute(param : Param) : Response
}
