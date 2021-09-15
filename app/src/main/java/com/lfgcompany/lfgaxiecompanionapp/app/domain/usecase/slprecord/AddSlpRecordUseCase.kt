package com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.slprecord

import android.util.Log
import com.lfgcompany.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.Settings
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.Interactor
import com.lfgcompany.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import java.util.*
import javax.inject.Inject

class AddSlpRecordUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val slpRecordGateway: SlpRecordGateway,
    private val scholarDataGateway: ScholarDataGateway,
    private val authenticationGateway: AuthenticationGateway,
    private val settings: Settings
) : Interactor<Unit, AddSlpRecordUseCase.Param>(interactorHandler) {
    override suspend fun run(params: AddSlpRecordUseCase.Param) {
        Log.e("Settings", settings.get("FIRST_TIME").toString())
        if (settings.get("FIRST_TIME")) {
            val initialSlp = authenticationGateway.getUser().initialSlp
            val currentSlp = scholarDataGateway.fetchScholarData(
                authenticationGateway.getUser().ronin
            ).inGameSlp
            if (initialSlp + params.slpRecord.amount != currentSlp) {
                throw LFGException("Your slp count is not true. Please recheck your value and try again.")
            } else {
                slpRecordGateway.saveRecordForToday(params.slpRecord)
                slpRecordGateway.syncInGameSlp(params.slpRecord.amount)
                settings.saveDate("DATE", Date().time)
                settings.save("FIRST_TIME", false)
            }
        } else {
            slpRecordGateway.saveRecordForToday(params.slpRecord)
            slpRecordGateway.syncInGameSlp(params.slpRecord.amount)
            settings.saveDate("DATE", Date().time)
        }
    }

    data class Param(
        val slpRecord: SlpRecord
    )
}