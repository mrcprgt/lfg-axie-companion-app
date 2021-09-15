package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase

import com.lfgcompany.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
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