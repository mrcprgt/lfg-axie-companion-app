package com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.GetUserUseCase
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.scholardata.FetchScholarDataUseCase
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord.GetSlpRecordAveragesUseCase
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord.GetSlpRecordsUseCase
import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class SlpRecordPresenter @Inject constructor(
    private val scopeProvider: CoroutineScopeProvider,
    private val fetchScholarDataUseCase: FetchScholarDataUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getSlpRecordsUseCase: GetSlpRecordsUseCase,
    private val getSlpRecordAveragesUseCase: GetSlpRecordAveragesUseCase
) : SlpRecordContract.Presenter {

    private var view: SlpRecordContract.View? = null

    private lateinit var scholarData: ScholarData
    private lateinit var user: User

    override fun onViewReady(view: SlpRecordContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        scopeProvider.provide().launch {
            view?.showProgressDialog("Please wait", "Getting your data...")
            user = getUserUseCase.execute(Unit).user
            scholarData = fetchScholarDataUseCase.execute(Unit).scholarData

            val currentCycleManagerShare = scholarData.inGameSlp * user.managerShare
            val currentCycleScholarShare = scholarData.inGameSlp * user.scholarShare

            view?.showCurrentCycle(
                currentCycleManagerShare,
                currentCycleScholarShare
            )

            val averages = getSlpRecordAveragesUseCase.execute(Unit)

            view?.showDaily(averages.daily)
            view?.showWeekly(averages.weekly)
            view?.showMonthly(averages.monthly)

            val records = getSlpRecordsUseCase.execute(GetSlpRecordsUseCase.Param(0)).slpRecords

            var lifeTimeSlp = 0
            records.forEach {
                lifeTimeSlp += it.amount
            }

            val lifetimeManagerShare =
                lifeTimeSlp * user.managerShare

            val lifetimeScholarShare =
                lifeTimeSlp * user.scholarShare

            view?.showLifetimeSlp(
                lifetimeManagerShare,
                lifetimeScholarShare
            )

            view?.appendList(records)
            view?.hideProgressDialog()
        }
    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onAddRecordClicked() {
        TODO("Not yet implemented")
    }

    override fun onSyncClicked() {
        TODO("Not yet implemented")
    }

}