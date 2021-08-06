package com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BaseView

interface SlpRecordContract {
    interface View : BaseView {
        fun showSlpRecords(slpRecords : List<SlpRecord>)
        fun showDaily(daily: Int)
        fun showWeekly(weekly: Int)
        fun showMonthly(monthly: Int)

        fun appendList(slpRecords: List<SlpRecord>)

    }

    interface Presenter : BasePresenter<View>{
        fun onAddRecordMenuClicked()
        fun onAddRecordClicked()
        fun onLoadMore(lastPosition: Int)
        fun onSyncClicked()
    }
}