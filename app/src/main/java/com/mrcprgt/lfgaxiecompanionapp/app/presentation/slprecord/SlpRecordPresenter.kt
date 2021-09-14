package com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.GetUserUseCase
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.scholardata.FetchScholarDataUseCase
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord.*
import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import com.mrcprgt.lfgaxiecompanionapp.tools.LFGException
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.Settings
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.dateIsToday
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume

class SlpRecordPresenter @Inject constructor(
    private val scopeProvider: CoroutineScopeProvider,
    private val fetchScholarDataUseCase: FetchScholarDataUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getSlpRecordsUseCase: GetSlpRecordsUseCase,
    private val getSlpRecordAveragesUseCase: GetSlpRecordAveragesUseCase,
    private val getLfgRecordAveragesUseCase: GetLfgRecordAveragesUseCase,
    private val addSlpRecordUseCase: AddSlpRecordUseCase,
    private val settings: Settings,
    private val fetchSlpGainsUseCase: FetchSlpGainsUseCase,
    private val getLfgSlpRecordUseCase: GetLfgSlpRecordUseCase
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

            fetchRecords()
            showCurrentClaim()

            val records = getLfgSlpRecordUseCase.execute(Unit).records

            showLifetime()
            showAverages()
           
            view?.appendList(records)
            view?.hideProgressDialog()
        }
    }

    private suspend fun showAverages() {
        val averages = getLfgRecordAveragesUseCase.execute(Unit)

        view?.showDaily(averages.daily)
        view?.showWeekly(averages.weekly)
        view?.showMonthly(averages.monthly)
    }

    override fun onViewDetach() {
        this.view = null
    }

    private suspend fun fetchRecords() {
        return suspendCancellableCoroutine {
            scopeProvider.provide().launch {
                try {
                    fetchSlpGainsUseCase.execute(Unit)
                    it.resume(Unit)
                } catch (e: LFGException) {
                    view?.showErrorDialog(
                        "Something went wrong",
                        e.message ?: e.localizedMessage,
                        onOkClicked = {
                            scopeProvider.provide().launch {
                                fetchRecords()
                            }
                        },
                        onDeclineClicked = {

                        }
                    )
                }
            }
        }
    }

    override fun onAddRecordClicked(slp: Int) {
        scopeProvider.provide().launch {
            if (dateIsToday(Date(settings.getDate("DATE")))) {
                view?.showToast("You already added a record today.")
            } else {
                try {
                    view?.showProgressDialog()
                    addSlpRecordUseCase.execute(
                        AddSlpRecordUseCase.Param(
                            SlpRecord(
                                Date(),
                                slp
                            )
                        )
                    )
                    view?.hideProgressDialog()
                    view?.hideAddDialog()
                    view?.clearSlp()

                    scholarData = fetchScholarDataUseCase.execute(Unit).scholarData

                    showCurrentClaim()
                    showLifetime()
                    showAverages()

                    view?.showSlpRecords(getSlpRecordsUseCase.execute(GetSlpRecordsUseCase.Param(0)).slpRecords)
                } catch (e: LFGException) {
                    view?.hideProgressDialog()
                    view?.showErrorDialog(
                        "Something went wrong",
                        e.localizedMessage ?: "Something went wrong",
                        onDeclineClicked = {
                        },
                        onOkClicked = {
                            onAddRecordClicked(slp)
                        }
                    )
                }
            }
        }
    }

    private fun showLifetime() {
        val lifeTimeSlp = scholarData.lastClaimAmount

        val lifetimeManagerShare =
            lifeTimeSlp * (user.managerShare * 0.01)

        val lifetimeScholarShare =
            lifeTimeSlp * (user.scholarShare * 0.01)

        view?.showLifetimeSlp(
            lifetimeManagerShare.toInt(),
            lifetimeScholarShare.toInt()
        )
    }

    private fun showCurrentClaim() {
        val currentCycleManagerShare = scholarData.inGameSlp * (user.managerShare * 0.01)
        val currentCycleScholarShare = scholarData.inGameSlp * (user.scholarShare * 0.01)

        view?.showCurrentCycle(
            currentCycleManagerShare.toInt(),
            currentCycleScholarShare.toInt()
        )
    }

    override fun onSyncClicked() {
        scopeProvider.provide().launch {
            try {
                view?.showProgressDialog("Please wait", "Fetching latest data...")
                scholarData = fetchScholarDataUseCase.execute(Unit).scholarData

                showCurrentClaim()
                showLifetime()
                showAverages()

                fetchRecords()
                val records = getLfgSlpRecordUseCase.execute(Unit).records
                view?.clearSlp()
                view?.appendList(records)
                view?.hideProgressDialog()
            } catch (e: LFGException) {
                view?.hideProgressDialog()
                view?.showErrorDialog(
                    "Something went wrong",
                    e.message ?: e.localizedMessage,
                    onOkClicked = {
                        onSyncClicked()
                    },
                    onDeclineClicked = {

                    }
                )
            }
        }
    }

}