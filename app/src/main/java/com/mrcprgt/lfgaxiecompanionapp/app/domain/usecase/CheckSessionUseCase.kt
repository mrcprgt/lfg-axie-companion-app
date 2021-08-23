package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.tools.LFGException
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val authenticationGateway: AuthenticationGateway
) : Interactor<Unit, Unit>(interactorHandler) {
    override suspend fun run(params: Unit) {
        try{
            authenticationGateway.getUser()
        }catch (e : LFGException){
            throw e
        }
    }

}