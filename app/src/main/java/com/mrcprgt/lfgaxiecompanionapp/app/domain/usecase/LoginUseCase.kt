package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val authenticationGateway: AuthenticationGateway
) : Interactor<Unit, LoginUseCase.Param>(interactorHandler) {
    override suspend fun run(params: LoginUseCase.Param): Unit {
        authenticationGateway.login(User(params.ronin, params.managerShare, params.scholarShare))
    }

    data class Param(
        val ronin: String,
        val managerShare: Int,
        val scholarShare: Int
    )
}