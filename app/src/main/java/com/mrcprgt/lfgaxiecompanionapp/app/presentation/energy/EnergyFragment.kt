package com.mrcprgt.lfgaxiecompanionapp.app.presentation.energy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentEnergyBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import javax.inject.Inject

class EnergyFragment : LFGFragment(), EnergyContract.View {

    @Inject
    lateinit var presenter: EnergyPresenter

    private val binding: FragmentEnergyBinding by lazy{
        FragmentEnergyBinding.inflate(layoutInflater)
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

    private fun setupListeners(){
        binding.btnAddMyEnergy.setOnClickWithDelay {
            presenter.onAddMyEnergyPressed()
        }

        binding.btnMinusMyEnergy.setOnClickWithDelay {
            presenter.onSubtractMyEnergyPressed()
        }

        binding.btnAddMyOpponentEnergy.setOnClickWithDelay {
            presenter.onAddEnemyEnergyPressed()
        }

        binding.btnMinusMyOpponentsEnergy.setOnClickWithDelay {
            presenter.onSubtractEnemyEnergyPressed()
        }

        binding.btnClearMatchEnergy.setOnClickWithDelay {
            presenter.onClearEnergyPressed()
        }
    }

    override fun updateMyEnergy(energy: Int) {
        binding.tvMyEnergy.text = energy.toString()
    }

    override fun updateOpponentEnergy(energy: Int) {
        binding.tvEnemyEnergy.text = energy.toString()
    }

    companion object {
        fun newInstance(): EnergyFragment {
            val args = Bundle()
            val fragment = EnergyFragment()
            fragment.arguments = args
            return fragment
        }
    }


}
