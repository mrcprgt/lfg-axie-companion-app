package com.mrcprgt.lfgaxiecompanionapp.app.presentation.stats

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentStatsBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.formatToMMDDYYYY
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import java.util.*
import javax.inject.Inject

class StatsFragment : LFGFragment(), StatsContract.View {

    @Inject
    lateinit var presenter: StatsPresenter

    private val binding: FragmentStatsBinding by lazy {
        FragmentStatsBinding.inflate(layoutInflater)
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

        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetach()
    }

    override fun showSlpCard(
        dailyAverage: Int,
        totalSlp: Int,
        managerShare: Int,
        scholarShare: Int
    ) {
        binding.tvDailyAverage.text = dailyAverage.toString()
        binding.tvTotalSlp.text = totalSlp.toString()
        binding.tvManagerShare.text = managerShare.toString()
        binding.tvScholarShare.text = scholarShare.toString()
    }

    @SuppressLint("SetTextI18n")
    override fun showClaimsCard(
        lastClaimedAmount: Int,
        lastClaimedAt: Date,
        nextClaimDate: Date,
        nextClaimIn: Int
    ) {
        binding.tvLastClaimedAmount.text = lastClaimedAmount.toString()
        binding.tvLastClaimedAt.text = lastClaimedAt.formatToMMDDYYYY()
        binding.tvNextClaimDate.text = nextClaimDate.formatToMMDDYYYY()
        binding.tvNextClaimInDays.text = "$nextClaimIn day(s)"
    }

    override fun showArenaCard(
        mmr: Int,
        wins: Int,
        draw: Int,
        lose: Int,
        winRate: Double,
        arenaRank: Int
    ) {
        binding.tvMmr.text = mmr.toString()
        binding.tvWins.text = wins.toString()
        binding.tvDraw.text = draw.toString()
        binding.tvLose.text = lose.toString()
        binding.tvWinRate.text = "$winRate %"
        binding.tvArenaRank.text = arenaRank.toString()
    }

    companion object{
        fun newInstance(): StatsFragment {
            val args = Bundle()
            val fragment = StatsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}