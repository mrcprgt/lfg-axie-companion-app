package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.Settings
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class AddSlpRecordUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val slpRecordGateway: SlpRecordGateway,
    private val authenticationGateway: AuthenticationGateway,
    private val settings: Settings
) : Interactor<Unit, AddSlpRecordUseCase.Param>(interactorHandler) {
    override suspend fun run(params: AddSlpRecordUseCase.Param) {
        if(settings.get("FIRST_TIME")){
            val initialSlp = authenticationGateway.getUser().initialSlp

        }
        slpRecordGateway.saveRecordForToday(params.slpRecord)
    }

    data class Param(
        val slpRecord: SlpRecord
    )
}