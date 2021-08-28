package com.mrcprgt.lfgaxiecompanionapp.app.presentation.login

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.CheckSessionUseCase
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.LoginUseCase
import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import com.mrcprgt.lfgaxiecompanionapp.tools.LFGException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkSessionUseCase: CheckSessionUseCase,
    private val scopeProvider: CoroutineScopeProvider
) : LoginContract.Presenter {

    private var view: LoginContract.View? = null

    private var validRonin = false
    private var validScholarShare = false
    private var validManagerShare = false
    private var validInventorySlp = false

    override fun onViewReady(view: LoginContract.View) {
        this.view = view

        scopeProvider.provide().launch {
            try {
                view?.showProgressDialog("Please wait", "Checking saved data...")
                delay(1000)
                checkSessionUseCase.execute(Unit)
                view?.hideProgressDialog()
                view?.navigateToHome()
            } catch (e: LFGException) {
               view?.hideProgressDialog()
            }
        }
    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onLoginClicked(ronin: String, managerShare: Int?, scholarShare: Int?, inventorySlp: Int?) {
        scopeProvider.provide().launch {
            view?.clearErrors()
            validateRonin(ronin)
            validateInventorySlp(inventorySlp)
            validateShares(managerShare, scholarShare)

            if (validManagerShare && validScholarShare && validRonin) {
                view?.showProgressDialog("Please wait", "Saving credentials")
                loginUseCase.execute(LoginUseCase.Param(ronin, managerShare!!, scholarShare!!, inventorySlp!!))
                view?.hideProgressDialog()
                view?.navigateToHome()
            }
        }
    }

    private fun validateRonin(ronin: String) {
        when {
            ronin.isNullOrBlank() -> {
                view?.showErrorRonin("Please enter a ronin address.")
            }
            ronin.length > 42 -> {
                view?.showErrorRonin("Too long for a ronin address.")
            }
            ronin.length < 42 -> {
                view?.showErrorRonin("Too short for a ronin address.")
            }
            ronin[0] != '0' && ronin[1] != 'x' -> {
                view?.showErrorRonin("Change ronin: to 0x.")
            }
            else -> {
                validRonin = true
            }
        }
    }

    private fun validateInventorySlp(slp: Int?){
        if(slp == null){
            view?.showErrorInventorySLP("Please enter the amount of slp in your inventory.")
        }else{
            validRonin = true
        }
    }

    private fun validateShares(managerShare: Int?, scholarShare: Int?) {
        when {
            managerShare == null -> {
                view?.showErrorManagerShare("Please enter manager's share.")
            }
            scholarShare == null -> {
                view?.showErrorScholarShare("Please enter your share.")
            }
//            managerShare == null && scholarShare == null -> {
//                view?.showErrorManagerShare("Please enter manager's share.")
//                view?.showErrorScholarShare("Please enter your share.")
//            }
            managerShare + scholarShare != 100 -> {
                view?.showToast((managerShare + scholarShare).toString())
                view?.showErrorManagerShare("Please total both values to 100.")
                view?.showErrorScholarShare("Please total both values to 100.")
            }
            else -> {
                validScholarShare = true
                validManagerShare = true
            }
        }
    }
}