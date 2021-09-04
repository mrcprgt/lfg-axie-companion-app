package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.LFGSlpRecordAndGainsGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
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