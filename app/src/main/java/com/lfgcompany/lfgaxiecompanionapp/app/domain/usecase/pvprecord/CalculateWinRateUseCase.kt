package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.pvprecord

import android.util.Log
import com.lfgcompany.lfgaxiecompanionapp.app.domain.PvpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class CalculateWinRateUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val gateway: PvpRecordGateway
) : Interactor<CalculateWinRateUseCase.Response, Unit>(interactorHandler) {
    data class Response(
        val winRate: Double
    )

    override suspend fun run(params: Unit): Response {
        val wins = gateway.getWins()
        val totalMatches = gateway.getTotalMatches()



        Log.e("Wins", wins.toString())
        Log.e("total matchest", totalMatches.toString())
        var winRate: Double = wins.toDouble() / totalMatches.toDouble()

        winRate = String.format("%.4f", winRate).toDouble()
        if (winRate.isNaN()) winRate = 0.0
        Log.e("win rate", winRate.toString())
        return Response(winRate = winRate)
    }
}