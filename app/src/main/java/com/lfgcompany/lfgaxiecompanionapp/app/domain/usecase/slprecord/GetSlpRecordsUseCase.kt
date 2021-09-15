package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.slprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class GetSlpRecordsUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val slpRecordGateway: SlpRecordGateway
) : Interactor<GetSlpRecordsUseCase.Response, GetSlpRecordsUseCase.Param>(interactorHandler) {

    override suspend fun run(params: Param): Response {
        return Response(
            slpRecordGateway.getRecords(params.offset)
        )
    }

    data class Response(
        val slpRecords: List<SlpRecord>
    )

    data class Param(
        val offset: Int
    )

}