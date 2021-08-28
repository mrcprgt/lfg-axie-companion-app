package com.mrcprgt.lfgaxiecompanionapp.app.presentation.login

import android.content.Intent
import android.os.Bundle
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.home.HomeActivity
import com.mrcprgt.lfgaxiecompanionapp.databinding.ActivityLoginBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGActivity
import javax.inject.Inject

class LoginActivity : LFGActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginPresenter

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter.onViewReady(this)

        binding.btnLogin.setOnClickListener {
            presenter.onLoginClicked(
                binding.inputRonin.editText!!.text.toString(),
                binding.inputManagerShare.editText!!.text.toString().toIntOrNull(),
                binding.inputScholarShare.editText!!.text.toString().toIntOrNull(),
                binding.inputInventorySLP.editText!!.text.toString().toIntOrNull(),
            )
        }
    }

    override fun closeApp() {
        finishAffinity()
    }

    override fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun showErrorRonin(message: String) {
        binding.inputRonin.isErrorEnabled = true
        binding.inputRonin.error = message
    }

    override fun showErrorManagerShare(message: String) {
        binding.inputManagerShare.isErrorEnabled = true
        binding.inputManagerShare.error = message

    }

    override fun showErrorScholarShare(message: String) {
        binding.inputScholarShare.isErrorEnabled = true
        binding.inputScholarShare.error = message
    }

    override fun showErrorInventorySLP(message: String) {
        binding.inputInventorySLP.isErrorEnabled = true
        binding.inputInventorySLP.error = message
    }

    override fun clearErrors() {
        binding.inputScholarShare.isErrorEnabled = false
        binding.inputScholarShare.error = ""
        binding.inputRonin.isErrorEnabled = false
        binding.inputRonin.error = ""
        binding.inputManagerShare.isErrorEnabled = false
        binding.inputManagerShare.error = ""
        binding.inputInventorySLP.isErrorEnabled = false
        binding.inputInventorySLP.error = ""

    }
}