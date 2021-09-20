package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase

import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.Settings
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class GetTrackingMethodUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val settings: Settings
) : Interactor<GetTrackingMethodUseCase.Response, Unit>(interactorHandler) {
    data class Response(
        val value: Boolean
    )

    override suspend fun run(params: Unit): Response {
        return Response(settings.getTracking())

    }


}
