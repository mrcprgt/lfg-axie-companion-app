package com.mrcprgt.lfgaxiecompanionapp.app.presentation.pvpbuddy

import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import javax.inject.Inject

class PvpBuddyPresenter @Inject constructor(private val scopeProvider: CoroutineScopeProvider) :
    PvpBuddyContract.Presenter {

    private var view: PvpBuddyContract.View? = null

    var energy: Int = 0
    var slpGain: Int = 0

    var totalSlpEarned = 0

    var wins: Int = 0
    var draws: Int = 0
    var loses: Int = 0

    override fun onViewReady(view: PvpBuddyContract.View) {
        this.view = view

        view?.updateEnergy(energy)
        view?.updateSlpGain(slpGain)

        view?.updateWins(wins)
        view?.updateDraws(draws)
        view?.updateLoses(loses)
        view?.updateTotalSlpEarned(totalSlpEarned)
    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onEnergyChanged(energy: Int) {
        this.energy = energy
        view?.updateEnergy(this.energy)
    }

    override fun onSlpGainChanged(slpGain: Int) {
        this.slpGain = slpGain
        view?.updateSlpGain(this.slpGain)
    }

    override fun onWinsPressed() {
        if(this.energy > 0){
            this.wins += 1
            this.energy -= 1
            this.totalSlpEarned += this.slpGain
            view?.updateWins(this.wins)
            view?.updateEnergy(this.energy)
            view?.updateTotalSlpEarned(this.totalSlpEarned)
        }else{
            view?.showToast("You are out of energy!")
        }

    }

    override fun onDrawsPressed() {
        if(this.energy > 0){
            this.draws += 1
            this.energy -= 1
            this.totalSlpEarned += (this.slpGain / 2)
            view?.updateDraws(this.wins)
            view?.updateEnergy(this.energy)
            view?.updateTotalSlpEarned(this.totalSlpEarned)
        }else{
            view?.showToast("You are out of energy!")
        }
    }

    override fun onLosesPressed() {
        if(this.energy > 0){
            this.loses += 1
            this.energy -= 1
            view?.updateLoses(this.wins)
            view?.updateEnergy(this.energy)
        }else{
            view?.showToast("You are out of energy!")
        }
    }

}