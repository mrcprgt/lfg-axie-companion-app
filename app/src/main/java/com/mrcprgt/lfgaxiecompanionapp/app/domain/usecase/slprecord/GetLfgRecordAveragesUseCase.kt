package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.LFGSlpRecordAndGainsGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class GetLfgRecordAveragesUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val gateway: LFGSlpRecordAndGainsGateway
) : Interactor<GetLfgRecordAveragesUseCase.Response, Unit>(interactorHandler) {


    data class Response(
        val daily: Int,
        val weekly: Int,
        val monthly: Int
    )

    override suspend fun run(params: Unit): GetLfgRecordAveragesUseCase.Response {
        return Response(
            gateway.getDailyTotals(),
            gateway.getWeeklyTotals(),
            gateway.getMonthlyTotals()
        )
    }

}
