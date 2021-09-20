package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase

import com.lfgcompany.lfgaxiecompanionapp.app.domain.LogoutGateway
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.Settings
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val logoutGateway: LogoutGateway,
    private val settings: Settings
) : Interactor<Unit, Unit>(interactorHandler) {
    override suspend fun run(params: Unit) {
        settings.clear()
        logoutGateway.logout()
    }
}