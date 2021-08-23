package com.mrcprgt.lfgaxiecompanionapp.app.presentation.energy

import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BaseView

interface EnergyContract {
    interface View : BaseView {
        fun updateMyEnergy(energy: Int)
        fun updateOpponentEnergy(energy: Int)
    }
    interface Presenter: BasePresenter<View> {
        fun onClearEnergyPressed()
        fun onAddMyEnergyPressed()
        fun onSubtractMyEnergyPressed()
        fun onAddEnemyEnergyPressed()
        fun onSubtractEnemyEnergyPressed()
    }
}