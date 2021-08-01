package com.mrcprgt.lfgaxiecompanionapp.app.presentation.login

import android.os.Debug
import android.util.Log
import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User
import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.Console
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val repo: AuthenticationGateway, private val scopeProvider: CoroutineScopeProvider): LoginContract.Presenter{
    private var view: LoginContract.View? = null

    override fun onViewReady(view: LoginContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onLoginClicked(ronin: String, managerShare: Int, scholarShare: Int) {
        scopeProvider.provide().launch {
            Log.e("LOGIN", "CAle")
            view?.showProgressDialog("Please wait", "Saving credentials")
            delay(1000)
            repo.login(User(ronin, managerShare, scholarShare))
            view?.hideProgressDialog()
            view?.showToast("Succes")
        }
    }

}