package com.mrcprgt.lfgaxiecompanionapp.app.presentation.pvpbuddy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord.SlpRecordFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord.SlpRecordPresenter
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentPvpBuddyBinding
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentSlpTrackerBinding
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
        setupListeners()
    }

    private fun setupListeners() {
        binding.inputEnergy.editText?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(e: Editable) {
                binding.inputEnergy.editText?.removeTextChangedListener(this)
                e.replace(0, e.length, e.toString())
                binding.inputEnergy.editText?.addTextChangedListener(this)
                presenter.onEnergyChanged(e.toString().toInt())
            }
        })
        binding.inputSlpGain.editText?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(e: Editable) {
                binding.inputSlpGain.editText?.removeTextChangedListener(this)
                e.replace(0, e.length, e.toString())
                binding.inputSlpGain.editText?.addTextChangedListener(this)
                presenter.onSlpGainChanged(e.toString().toInt())
            }
        })
        binding.btnAddWin.setOnClickListener {
            presenter.onWinsPressed()
        }
        binding.btnAddDraw.setOnClickListener {
            presenter.onDrawsPressed()
        }
        binding.btnAddLose.setOnClickListener {
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
        binding.tvDraws.text = draws.toString()
    }

    override fun updateLoses(loses: Int) {
        binding.tvLoses.text = loses.toString()
    }

    override fun updateTotalSlpEarned(totalSlp: Int) {
        binding.tvTotalSlpEarned.text = totalSlp.toString()
    }

    override fun clearRecord() {
        TODO("Not yet implemented")
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