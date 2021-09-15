package com.lfgcompany.lfgaxiecompanionapp.app.presentation.energy

import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BaseView

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