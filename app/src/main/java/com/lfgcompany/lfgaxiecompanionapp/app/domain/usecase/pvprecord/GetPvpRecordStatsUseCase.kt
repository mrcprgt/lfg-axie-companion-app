package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.pvprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.PvpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class GetPvpRecordStatsUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val gateway: PvpRecordGateway
) : Interactor<GetPvpRecordStatsUseCase.Response, Unit>(interactorHandler) {

    data class Response(
        val wins: Int,
        val draws: Int,
        val loses: Int
    )

    override suspend fun run(params: Unit): Response {
        return Response(
            wins = gateway.getWins(),
            draws = gateway.getDraws(),
            loses = gateway.getLoses()
        )
    }
}