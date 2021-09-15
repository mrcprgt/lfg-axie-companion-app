package com.lfgcompany.lfgaxiecompanionapp.app.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.home.HomeActivity
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.login.LoginActivity
import com.lfgcompany.lfgaxiecompanionapp.databinding.ActivitySplashBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.LFGActivity
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : LFGActivity(), SplashContract.View {
    @Inject
    lateinit var presenter: SplashPresenter

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter.onViewReady(this)
    }

    override fun closeApp() {
        finishAffinity()
    }

    override fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}