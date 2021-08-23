package com.mrcprgt.lfgaxiecompanionapp.app.presentation.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.buffs.BuffsFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.energy.EnergyFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.gains.GainsFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.pvpbuddy.PvpBuddyFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord.SlpRecordFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.stats.StatsFragment
import com.mrcprgt.lfgaxiecompanionapp.tools.LFGException

class BottomNavViewPagerAdapter(
    fragmentActivity: HomeActivity?
) : FragmentStateAdapter(fragmentActivity!!) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            STATS_INDEX -> StatsFragment.newInstance()
            SLP_RECORD_INDEX -> SlpRecordFragment.newInstance()
            PVP_BUDDY_INDEX -> PvpBuddyFragment.newInstance()
            BUFFS_INDEX -> BuffsFragment.newInstance()
            GAINS_INDEX -> GainsFragment.newInstance()
            ENERGY_INDEX -> EnergyFragment.newInstance()
//            CARDS_INDEX -> GainsFragment.newInstance()
//            CALCULATOR_INDEX -> GainsFragment.newInstance()
            else -> throw LFGException("Failed to get order fragment!")
        }
    }

    override fun getItemCount(): Int {
        return TAB_LENGTH
    }

    companion object {
        private const val TAB_LENGTH = 6
//        private const val TAB_LENGTH = 7
        private const val STATS_INDEX = 0
        private const val SLP_RECORD_INDEX = 1
        private const val PVP_BUDDY_INDEX = 2
        private const val GAINS_INDEX = 3
        private const val BUFFS_INDEX = 4
        private const val ENERGY_INDEX = 5
//        private const val CARDS_INDEX = 6
//        private const val CALCULATOR_INDEX = 7
    }
}
