package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.slprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class GetSlpRecordAveragesUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val slpRecordGateway: SlpRecordGateway
) : Interactor<GetSlpRecordAveragesUseCase.Response, Unit>(interactorHandler) {


    data class Response(
        val daily: Int,
        val weekly: Int,
        val monthly: Int
    )

    override suspend fun run(params: Unit): Response {
        return Response(
            slpRecordGateway.getDailyTotals(),
            slpRecordGateway.getWeeklyTotals(),
            slpRecordGateway.getMonthlyTotals()
        )
    }

}