package com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BaseView

interface PvpBuddyContract {
    interface View : BaseView {
        fun updateWins(wins: Int)
        fun updateDraws(draws: Int)
        fun updateLoses(loses: Int)


        fun updateTotalMatchesPlayed(matchesPlayed: Int)
        fun updateWinRate(winRate: Double)
        fun updateTotalSlpEarned(totalSlp: Int)

        fun updateList(list: List<PvpRecord>)
        fun clearList()

        fun dismissDialogs()
    }

    interface Presenter : BasePresenter<View> {
        fun onClearClicked()
        fun onAddPressed(pvpResult: PvpRecord.PvpResult, slp: Int)
    }
}