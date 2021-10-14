package com.lfgcompany.lfgaxiecompanionapp.app.presentation.login

import com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.CheckSessionUseCase
import com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.LoginUseCase
import com.lfgcompany.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkSessionUseCase: CheckSessionUseCase,
    private val scopeProvider: CoroutineScopeProvider
) : LoginContract.Presenter {

    private var view: LoginContract.View? = null

    private var hasError = false

    override fun onViewReady(view: LoginContract.View) {
        this.view = view

//        setup()
    }

//    private fun setup() {
//        scopeProvider.provide().launch {
//            try {
//                view?.showProgressDialog("Please wait", "Checking saved data...")
//                delay(1000)
//                checkSessionUseCase.execute(Unit)
//                view?.hideProgressDialog()
//                view?.navigateToHome()
//            } catch (e: LFGException) {
//                view?.hideProgressDialog()
//            }
//        }
//    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onLoginClicked(
        ronin: String,
        managerShare: Int?,
        scholarShare: Int?,
        inventorySlp: Int?
    ) {
        scopeProvider.provide().launch {
            try {
                hasError = false
                view?.clearErrors()
                validateRonin(ronin)
                validateInventorySlp(inventorySlp)
                validateShares(managerShare, scholarShare)

                if (!hasError) {
                    view?.showProgressDialog("Please wait", "Saving credentials...")
                    loginUseCase.execute(
                        LoginUseCase.Param(
                            ronin,
                            managerShare!!,
                            scholarShare!!,
                            inventorySlp!!
                        )
                    )
                    view?.hideProgressDialog()
                    view?.navigateToHome()
                }
            } catch (e: NullPointerException) {
                view?.hideProgressDialog()
                view?.showMessageDialog(
                    "Invalid Ronin Address!",
                    "This ronin address does not have any axies on it.\nPlease double check your input. If you're a scholar, be sure to provide your SCHOLAR ronin address not your personal address.",
                    onOkClicked = {

                    }
                )
            }
        }
    }

    private fun validateRonin(ronin: String) {
        when {
            ronin.isBlank() -> {
                view?.showErrorRonin("Please enter a ronin address.")
                hasError = true
            }
            ronin.length > 42 -> {
                view?.showErrorRonin("Too long for a ronin address.")
                hasError = true
            }
            ronin.length < 42 -> {
                view?.showErrorRonin("Too short for a ronin address.")
                hasError = true
            }
            ronin[0] != '0' && ronin[1] != 'x' -> {
                view?.showErrorRonin("Change ronin: to 0x.")
                hasError = true
            }
        }
    }

    private fun validateInventorySlp(slp: Int?) {
        if (slp == null) {
            view?.showErrorInventorySLP("Please enter the amount of slp in your inventory.")
            hasError = true
        }
    }

    private fun validateShares(managerShare: Int?, scholarShare: Int?) {
        when {
            managerShare == null -> {
                view?.showErrorManagerShare("Please enter manager's share.")
                hasError = true
            }
            scholarShare == null -> {
                view?.showErrorScholarShare("Please enter your share.")
                hasError = true
            }
//            managerShare == null && scholarShare == null -> {
//                view?.showErrorManagerShare("Please enter manager's share.")
//                view?.showErrorScholarShare("Please enter your share.")
//            }
            managerShare + scholarShare != 100 -> {
                hasError = true
                view?.showToast((managerShare + scholarShare).toString())
                view?.showErrorManagerShare("Please total both values to 100.")
                view?.showErrorScholarShare("Please total both values to 100.")
            }
        }
    }
}