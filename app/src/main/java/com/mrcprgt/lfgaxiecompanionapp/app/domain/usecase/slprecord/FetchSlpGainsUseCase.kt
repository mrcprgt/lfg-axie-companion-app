package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.LFGSlpRecordAndGainsGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class FetchSlpGainsUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val lfgSlpRecordAndGainsGateway: LFGSlpRecordAndGainsGateway,
    private val authenticationGateway: AuthenticationGateway
) : Interactor<FetchSlpGainsUseCase.Response, Unit>(interactorHandler) {

    override suspend fun run(params: Unit): Response {
        val ronin = authenticationGateway.getUser().ronin
        val data = lfgSlpRecordAndGainsGateway.fetchRecordsAndGains(ronin)
        lfgSlpRecordAndGainsGateway.save(data)

        return Response(data)
    }

    data class Response(
        val gains: List<LFGSlpRecordAndGains>
    )

}