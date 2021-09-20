package com.lfgcompany.lfgaxiecompanionapp.app.presentation.slprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BaseView

interface SlpRecordContract {
    interface View : BaseView {
        fun showSlpRecords(slpRecords: List<SlpRecord>)
        fun showDaily(daily: Int)
        fun showWeekly(weekly: Int)
        fun showMonthly(monthly: Int)

        fun showCurrentCycle(manager: Int, scholar: Int)
        fun showLifetimeSlp(manager: Int, scholar: Int)

        fun appendList(slpRecords: List<LFGSlpRecordAndGains>)
        fun hideAddDialog()

        fun clearSlp()

        fun showAddDialog()
    }

    interface Presenter : BasePresenter<View> {
        fun onAddButtonClicked()
        fun onAddRecordClicked(slp: Int)
        fun onSyncClicked()
    }
}