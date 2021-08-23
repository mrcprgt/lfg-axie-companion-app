package com.mrcprgt.lfgaxiecompanionapp.app.presentation.energy

import com.mrcprgt.lfgaxiecompanionapp.app.presentation.energy.EnergyContract
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.pvpbuddy.PvpBuddyContract
import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import javax.inject.Inject

class EnergyPresenter @Inject constructor() :
    EnergyContract.Presenter {

    private var view: EnergyContract.View? = null

    var myEnergy: Int = 3
    var enemyEnergy: Int = 3

    override fun onViewReady(view: EnergyContract.View) {
        this.view = view

        view.updateMyEnergy(myEnergy)
        view.updateOpponentEnergy(enemyEnergy)
    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onClearEnergyPressed() {
        myEnergy = 3
        enemyEnergy = 3
        view?.updateMyEnergy(myEnergy)
        view?.updateOpponentEnergy(enemyEnergy)
    }

    override fun onAddMyEnergyPressed() {
        if(myEnergy < 10){
            myEnergy += 1
        }
        view?.updateMyEnergy(myEnergy)
    }

    override fun onSubtractMyEnergyPressed() {
        if(myEnergy > 0){
            myEnergy -= 1
        }
        view?.updateMyEnergy(myEnergy)
    }

    override fun onAddEnemyEnergyPressed() {
        if(enemyEnergy < 10){
            enemyEnergy += 1
        }
        view?.updateOpponentEnergy(enemyEnergy)
    }

    override fun onSubtractEnemyEnergyPressed() {
        if(enemyEnergy > 0){
            enemyEnergy -= 1
        }
        view?.updateOpponentEnergy(enemyEnergy)
    }

}
