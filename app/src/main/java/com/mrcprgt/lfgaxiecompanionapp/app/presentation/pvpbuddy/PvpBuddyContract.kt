package com.mrcprgt.lfgaxiecompanionapp.app.presentation.pvpbuddy

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BaseView

interface PvpBuddyContract {
    interface View: BaseView {
        fun updateEnergy(energy : Int)
        fun updateSlpGain(slpGain: Int)

        fun updateWins(wins: Int)
        fun updateDraws(draws: Int)
        fun updateLoses(loses: Int)

        fun updateTotalSlpEarned(totalSlp: Int)

        fun clearRecord()

    }

    interface Presenter: BasePresenter<View>{
        fun onEnergyChanged(energy: Int)
        fun onSlpGainChanged(slpGain: Int)
        fun onWinsPressed()
        fun onDrawsPressed()
        fun onLosesPressed()
    }
}