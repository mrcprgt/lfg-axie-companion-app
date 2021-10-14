package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.pvprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.PvpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class AddPvpRecordUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val pvpRecordGateway: PvpRecordGateway
) : Interactor<Unit, AddPvpRecordUseCase.Param>(interactorHandler) {
    data class Param(
        val pvpRecord: PvpRecord
    )

    override suspend fun run(params: Param) {
        pvpRecordGateway.addRecord(params.pvpRecord)
    }
}