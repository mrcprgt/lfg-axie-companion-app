package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
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