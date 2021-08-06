package com.mrcprgt.lfgaxiecompanionapp.app.presentation.login

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User
import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val repo: AuthenticationGateway,
    private val scopeProvider: CoroutineScopeProvider
) : LoginContract.Presenter {

    private var view: LoginContract.View? = null

    private var validRonin = false
    private var validScholarShare = false
    private var validManagerShare = false

    override fun onViewReady(view: LoginContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onLoginClicked(ronin: String, managerShare: Int?, scholarShare: Int?) {
        scopeProvider.provide().launch {
            view?.clearErrors()
            validateRonin(ronin)
            validateShares(managerShare,scholarShare)

            if (validManagerShare && validScholarShare && validRonin) {
                view?.showProgressDialog("Please wait", "Saving credentials")
                delay(1000)
                repo.login(User(ronin, managerShare!!, scholarShare!!))
                view?.hideProgressDialog()
                view?.showToast("Succes")
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
            else -> {
                validRonin = true
            }
        }
    }

    private fun validateShares(managerShare: Int?, scholarShare: Int?){
        when{
            managerShare == null ->{
                view?.showErrorManagerShare("Please enter manager's share.")
            }
            scholarShare == null ->{
                view?.showErrorScholarShare("Please enter your share.")
            }
            managerShare == null && scholarShare == null ->{

            }
            managerShare!! + scholarShare!! != 100 -> {
                view?.showErrorManagerShare("Please total both values to 100.")
                view?.showErrorScholarShare("Please total both values to 100.")
            }
            else -> {
                validScholarShare=true
                validManagerShare=true
            }
        }
    }
}