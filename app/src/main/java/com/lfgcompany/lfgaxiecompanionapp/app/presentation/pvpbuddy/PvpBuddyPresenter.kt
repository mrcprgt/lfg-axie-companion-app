package com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.pvprecord.*
import com.lfgcompany.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class PvpBuddyPresenter @Inject constructor(
    private val scopeProvider: CoroutineScopeProvider,
    private val addPvpRecordUseCase: AddPvpRecordUseCase,
    private val getPvpRecordsUseCase: GetPvpRecordsUseCase,
    private val getPvpRecordStatsUseCase: GetPvpRecordStatsUseCase,
    private val calculateWinRateUseCase: CalculateWinRateUseCase,
    private val clearPvpRecordsUseCase: ClearPvpRecordsUseCase
) : PvpBuddyContract.Presenter {
    private var view: PvpBuddyContract.View? = null

    override fun onViewReady(view: PvpBuddyContract.View) {
        this.view = view

        setup()
    }

    private fun setup() {
        scopeProvider.provide().launch {
            view?.showProgressDialog()
            updateViews()
            updateList()
            view?.hideProgressDialog()
        }
    }

    override fun onViewDetach() {
        this.view = null
    }

    private suspend fun updateList() {
        val list = getPvpRecordsUseCase.execute(Unit).pvpRecords
        var slpEarned = 0
        list.forEach {
            slpEarned += it.slpEarned
        }
        view?.updateTotalSlpEarned(slpEarned)
        view?.updateList(list)
    }

    override fun onClearClicked() {
        scopeProvider.provide().launch {
            try {
                view?.showProgressDialog()
                clearPvpRecordsUseCase.execute(Unit)
                delay(1000)
                updateViews()
                updateList()
                view?.hideProgressDialog()
            } catch (e: LFGException) {
                view?.hideProgressDialog()
                view?.showErrorDialog(
                    "Something wrong",
                    e.message ?: e.localizedMessage,
                    onOkClicked = {
                        onClearClicked()
                    },
                    onDeclineClicked = {

                    }
                )
            }
        }
    }

    private suspend fun updateViews() {
        view?.showProgressDialog()
        val response = getPvpRecordStatsUseCase.execute(Unit)
        val wins = response.wins
        val draws = response.draws
        val lose = response.loses

        val winRate = calculateWinRateUseCase.execute(Unit).winRate

        view?.updateTotalMatchesPlayed(wins + draws + lose)
        view?.updateWinRate(winRate)
        view?.updateWins(wins)
        view?.updateDraws(draws)
        view?.updateLoses(lose)
        view?.hideProgressDialog()
    }

    override fun onAddPressed(pvpResult: PvpRecord.PvpResult, slp: Int) {
        scopeProvider.provide().launch {
            try {
                view?.showProgressDialog()
                addPvpRecordUseCase.execute(
                    AddPvpRecordUseCase.Param(
                        PvpRecord(
                            0,
                            pvpResult,
                            Date(),
                            slp
                        )
                    )
                )
                updateList()
                updateViews()
                view?.dismissDialogs()
            } catch (e: LFGException) {
                view?.hideProgressDialog()
                view?.showErrorDialog(
                    "Something went wrong",
                    e.message ?: e.localizedMessage,
                    onOkClicked = {
                        onAddPressed(pvpResult, slp)
                    },
                    onDeclineClicked = {

                    }
                )
            }
        }
    }


}