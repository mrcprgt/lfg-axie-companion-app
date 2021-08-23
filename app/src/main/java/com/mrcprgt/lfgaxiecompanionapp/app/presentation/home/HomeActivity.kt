package com.mrcprgt.lfgaxiecompanionapp.app.presentation.home

import android.os.Bundle
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

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.offscreenPageLimit = 1

        TabLayoutMediator(
            binding.tabBottom,
            binding.viewPager,
            TabConfiguration()
        ).attach()
    }

    private class TabConfiguration : TabLayoutMediator.TabConfigurationStrategy {
        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
            when (position) {
                0 -> {
                    tab.text = "Stats"
                    tab.setIcon(R.drawable.ic_baseline_person_24)
                }
                1 -> {
                    tab.text = "SLP"
                    tab.setIcon(R.drawable.icon_slp___copy)
                }
                2 -> {
                    tab.text = "PvP"
                    tab.setIcon(R.drawable.ic_baseline_construction_24)
                }
                3 -> {
                    tab.text = "Gains"
                    tab.setIcon(R.drawable.ic_baseline_show_chart_24)
                }
                4 -> {
                    tab.text = "Buffs"
                    tab.setIcon(R.drawable.buff_attack_up)
                }
                4 -> {
                    tab.text = "Buffs"
                    tab.setIcon(R.drawable.buff_attack_up)
                }
                5 -> {
                    tab.text = "Energy"
                    tab.setIcon(R.drawable.buff_attack_up)
                }
                6 -> {
                    tab.text = "Cards"
                    tab.setIcon(R.drawable.buff_attack_up)
                }
                7 -> {
                    tab.text = "Calculator"
                    tab.setIcon(R.drawable.buff_attack_up)
                }
            }

        }
    }
}