package com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy

import com.lfgcompany.lfgaxiecompanionapp.tools.CoroutineScopeProvider
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

        setup()
    }

    private fun setup() {
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


    override fun onSavePressed(energy: Int, slpGain: Int) {
        this.energy = energy
        view?.updateEnergy(this.energy)
        this.slpGain = slpGain
        view?.updateSlpGain(this.slpGain)

        view?.showToast("Values have been saved! :)")
    }

    override fun onWinsPressed() {
        if (this.energy > 0) {
            this.wins += 1
            this.energy -= 1
            this.totalSlpEarned += this.slpGain
            view?.updateWins(this.wins)
            view?.updateEnergy(this.energy)
            view?.updateTotalSlpEarned(this.totalSlpEarned)
        } else {
            view?.showToast("You are out of energy!")
        }

    }

    override fun onDrawsPressed() {
        if (this.energy > 0) {
            this.draws += 1
            this.energy -= 1
            this.totalSlpEarned += (this.slpGain / 2)
            view?.updateDraws(this.draws)
            view?.updateEnergy(this.energy)
            view?.updateTotalSlpEarned(this.totalSlpEarned)
        } else {
            view?.showToast("You are out of energy!")
        }
    }

    override fun onLosesPressed() {
        if (this.energy > 0) {
            this.loses += 1
            this.energy -= 1
            view?.updateLoses(this.loses)
            view?.updateEnergy(this.energy)
        } else {
            view?.showToast("You are out of energy!")
        }
    }
}