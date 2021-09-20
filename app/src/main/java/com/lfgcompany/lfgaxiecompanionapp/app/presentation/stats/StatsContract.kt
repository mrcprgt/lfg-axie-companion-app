package com.lfgcompany.lfgaxiecompanionapp.app.presentation.stats

import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BaseView
import java.util.*

interface StatsContract {
    interface View : BaseView {
        fun showSlpCard(dailyAverage: Int, totalSlp: Int, managerShare: Int, scholarShare: Int)
        fun showClaimsCard(
            lastClaimedAmount: Int,
            lastClaimedAt: Date,
            nextClaimDate: Date,
            nextClaimIn: Int
        )

        fun showArenaCard(
            mmr: Int,
            wins: Int,
            draw: Int,
            lose: Int,
            winRate: Double,
            arenaRank: Int
        )

        fun showSettingsBottomSheet()
        fun navigateToLogin()
    }

    interface Presenter : BasePresenter<View> {
        fun onViewCreated()
        fun onRefreshClicked()
        fun onSettingsClicked()
        fun onLogoutClicked()
        fun onChangeTrackingClicked()
    }
}