package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.slprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.LFGSlpRecordAndGainsGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class GetLfgSlpRecordUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val gateway: LFGSlpRecordAndGainsGateway
) : Interactor<GetLfgSlpRecordUseCase.Response, Unit>(interactorHandler) {

    data class Response(
        val records: List<LFGSlpRecordAndGains>
    )

    override suspend fun run(params: Unit): Response {
        return Response(
            gateway.get(0)
        )
    }
}