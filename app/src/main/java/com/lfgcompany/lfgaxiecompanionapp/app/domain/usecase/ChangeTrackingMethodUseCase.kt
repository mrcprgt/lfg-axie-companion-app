package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase

import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.Settings
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class ChangeTrackingMethodUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val settings: Settings
) : Interactor<Unit, ChangeTrackingMethodUseCase.Param>(interactorHandler) {
    data class Param(
        val value: Boolean
    )


    override suspend fun run(params: Param) {
        settings.changeTracking(params.value)
    }
}