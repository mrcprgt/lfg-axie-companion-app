package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val authenticationGateway: AuthenticationGateway
): Interactor<GetUserUseCase.Response, Unit>(interactorHandler){

    data class Response(
        val user: User
    )

    override suspend fun run(params: Unit): Response {
        return Response(authenticationGateway.getUser())
    }
}