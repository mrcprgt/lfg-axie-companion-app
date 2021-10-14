package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.pvprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.PvpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class ClearPvpRecordsUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val gateway: PvpRecordGateway
) : Interactor<Unit, Unit>(interactorHandler) {
    override suspend fun run(params: Unit) {
        gateway.clearRecords()
    }

}