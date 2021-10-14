package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.pvprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.PvpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class GetPvpRecordsUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val gateway: PvpRecordGateway
) : Interactor<GetPvpRecordsUseCase.Response, Unit>(interactorHandler) {
    data class Response(
        val pvpRecords: List<PvpRecord>
    )

    override suspend fun run(params: Unit): Response {
        return Response(gateway.getRecords())
    }
}