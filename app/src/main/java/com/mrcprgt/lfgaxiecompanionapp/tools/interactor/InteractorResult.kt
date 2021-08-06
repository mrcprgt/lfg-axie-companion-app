package com.mrcprgt.lfgaxiecompanionapp.tools.interactor

sealed class InteractorResult<out S, out F> where F :  Exception {

    data class Success<out S>(val data: S): InteractorResult<S, Nothing>()

    data class Failure<out F : Exception>(val error: F): InteractorResult<Nothing, F>()
}

