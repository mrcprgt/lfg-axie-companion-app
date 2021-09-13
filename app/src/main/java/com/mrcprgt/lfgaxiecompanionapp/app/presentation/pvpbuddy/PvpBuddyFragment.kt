package com.mrcprgt.lfgaxiecompanionapp.app.presentation.pvpbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentPvpBuddyBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import javax.inject.Inject

class PvpBuddyFragment : LFGFragment(), PvpBuddyContract.View {

    @Inject
    lateinit var presenter: PvpBuddyPresenter

    private val binding: FragmentPvpBuddyBinding by lazy {
        FragmentPvpBuddyBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onViewReady(this)

        binding.parent.requestDisallowInterceptTouchEvent(true)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickWithDelay {
            presenter.onSavePressed(
                binding.inputEnergy.editText!!.text.toString().toIntOrNull() ?: 0,
                binding.inputSlpGain.editText!!.text.toString().toIntOrNull() ?: 0
            )
        }

        binding.btnAddWin.setOnClickWithDelay {
            presenter.onWinsPressed()
        }
        binding.btnAddDraw.setOnClickWithDelay {
            presenter.onDrawsPressed()
        }
        binding.btnAddLose.setOnClickWithDelay {
            presenter.onLosesPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetach()
    }

    override fun updateEnergy(energy: Int) {
        binding.inputEnergy.editText?.setText(energy.toString())
    }

    override fun updateSlpGain(slpGain: Int) {
        binding.inputSlpGain.editText?.setText(slpGain.toString())
    }

    override fun updateWins(wins: Int) {
        binding.tvWins.text = wins.toString()
    }

    override fun updateDraws(draws: Int) {
        binding.tvDraw.text = draws.toString()
    }

    override fun updateLoses(loses: Int) {
        binding.tvLose.text = loses.toString()
    }

    override fun updateTotalSlpEarned(totalSlp: Int) {
        binding.tvTotalSlpEarned.text = totalSlp.toString()
    }

    companion object {
        fun newInstance(): PvpBuddyFragment {
            val args = Bundle()
            val fragment = PvpBuddyFragment()
            fragment.arguments = args
            return fragment
        }
    }
}