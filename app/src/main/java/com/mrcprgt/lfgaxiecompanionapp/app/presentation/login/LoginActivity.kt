package com.mrcprgt.lfgaxiecompanionapp.app.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrcprgt.lfgaxiecompanionapp.R
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
                binding.inputManagerShare.editText!!.text.toString().toInt(),
                binding.inputManagerShare.editText!!.text.toString().toInt(),
            )
        }
    }

    override fun closeApp() {
        TODO("Not yet implemented")
    }

    override fun navigateToHome() {
        TODO("Not yet implemented")
    }
}