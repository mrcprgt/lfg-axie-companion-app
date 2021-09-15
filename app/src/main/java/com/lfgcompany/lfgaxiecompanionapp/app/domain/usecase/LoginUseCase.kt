package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase

import com.lfgcompany.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.User
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.Settings
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val authenticationGateway: AuthenticationGateway,
    private val scholarDataGateway: ScholarDataGateway,
    private val settings: Settings
) : Interactor<Unit, LoginUseCase.Param>(interactorHandler) {
    override suspend fun run(params: Param) {
        val inGameSlp = scholarDataGateway.fetchScholarData(ronin = params.ronin).inGameSlp
        authenticationGateway.login(
            User(
                params.ronin,
                params.managerShare,
                params.scholarShare,
                inGameSlp
            )
        )
        settings.save("FIRST_TIME", true)
    }

    data class Param(
        val ronin: String,
        val managerShare: Int,
        val scholarShare: Int,
        val initialSlp: Int
    )
}