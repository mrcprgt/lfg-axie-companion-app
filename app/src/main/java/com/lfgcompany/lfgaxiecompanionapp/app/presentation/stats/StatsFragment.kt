package com.lfgcompany.lfgaxiecompanionapp.app.presentation.stats

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.home.HomeActivity
import com.lfgcompany.lfgaxiecompanionapp.databinding.FragmentStatsBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.formatToMMDDYYYY
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.showAllowingStateLoss
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import java.util.*
import javax.inject.Inject

class StatsFragment : LFGFragment(), StatsContract.View, MenuBottomSheetListener {

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

        binding.btnRefresh.setOnClickWithDelay {
            presenter.onRefreshClicked()
        }

        binding.btnSettings.setOnClickWithDelay {
            presenter.onSettingsClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetach()
    }

    @SuppressLint("SetTextI18n")
    override fun showSlpCard(
        dailyAverage: Int,
        totalSlp: Int,
        managerShare: Int,
        scholarShare: Int
    ) {
        binding.tvDailyAverage.text = dailyAverage.toString()
        binding.tvTotalSlp.text = totalSlp.toString()
        binding.tvManagerShare.text = "$managerShare %"
        binding.tvScholarShare.text = "$scholarShare %"
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
        if (nextClaimIn == 0) {
            binding.tvNextClaimInDays.text = "Today"
        } else {
            binding.tvNextClaimInDays.text = "$nextClaimIn day(s)"
        }
    }

    @SuppressLint("SetTextI18n")
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
        binding.tvWinRate.text = "%.2f".format(winRate) + " %"
        binding.tvArenaRank.text = arenaRank.toString()
    }

    override fun showSettingsBottomSheet() {
        MenuBottomSheet.newInstance(this)
            .showAllowingStateLoss(parentFragmentManager, "menubottomsheet")
    }

    override fun navigateToLogin() {
        startActivity(Intent(requireContext(), HomeActivity::class.java))
    }

    companion object {
        fun newInstance(): StatsFragment {
            val args = Bundle()
            val fragment = StatsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onLogoutClicked() {
        presenter.onLogoutClicked()
    }

    override fun onChangeTrackingClick() {
        presenter.onChangeTrackingClicked()
    }

}