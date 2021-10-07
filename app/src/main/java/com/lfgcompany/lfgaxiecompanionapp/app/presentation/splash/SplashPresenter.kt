package com.lfgcompany.lfgaxiecompanionapp.app.presentation.splash

import com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.CheckSessionUseCase
import com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.LogoutUseCase
import com.lfgcompany.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException
import com.lfgcompany.lfgaxiecompanionapp.tools.scopes.NoSessionException
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val scopeProvider: CoroutineScopeProvider,
    private val checkSessionUseCase: CheckSessionUseCase,
    private val logoutUseCase: LogoutUseCase,
) : SplashContract.Presenter {

    private var view: SplashContract.View? = null

    override fun onViewReady(view: SplashContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        scopeProvider.provide().launch {
            try {
                checkSessionUseCase.execute(Unit)
                view?.navigateToHome()
            } catch (e: LFGException) {
                if (e is NoSessionException) {
                    logoutUseCase.execute(Unit)
                    view?.navigateToLogin()
                } else {
                    view?.navigateToLogin()
                }
            }
        }
    }

    override fun onViewDetach() {
        this.view = null
    }
}