package com.mrcprgt.lfgaxiecompanionapp.app.presentation.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mrcprgt.lfgaxiecompanionapp.R
import com.mrcprgt.lfgaxiecompanionapp.databinding.ActivityMainBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGActivity

class HomeActivity : LFGActivity(), HomeContract.View {
    private val viewPagerAdapter = BottomNavViewPagerAdapter(this)

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setupViewPager()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//    }

    private fun setupViewPager() {
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.offscreenPageLimit = 1

        TabLayoutMediator(
            binding.tabBottom,
            binding.viewPager,
            TabConfiguration()
        ).attach()

        binding.tabBottom.setSelectedTabIndicator(R.color.transparent)
    }

    private class TabConfiguration : TabLayoutMediator.TabConfigurationStrategy {
        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
            when (position) {
                0 -> {
                    tab.text = "Stats"
                    tab.setIcon(R.drawable.stats_icon)
                }
                1 -> {
                    tab.text = "SLP"
                    tab.setIcon(R.drawable.slp_icon)
                }
                2 -> {
                    tab.text = "PvP"
                    tab.setIcon(R.drawable.pvp_icon)
                }
                3 -> {
                    tab.text = "Gains"
                    tab.setIcon(R.drawable.gains_icon)
                }
                4 -> {
                    tab.text = "Buffs"
                    tab.setIcon(R.drawable.buffs_icon)
                }
            }

        }
    }
}