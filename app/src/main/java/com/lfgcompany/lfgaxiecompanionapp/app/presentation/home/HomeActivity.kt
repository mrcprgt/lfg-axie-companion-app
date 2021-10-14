package com.lfgcompany.lfgaxiecompanionapp.app.presentation.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lfgcompany.lfgaxiecompanionapp.R
import com.lfgcompany.lfgaxiecompanionapp.databinding.ActivityMainBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.LFGActivity
import kotlin.random.Random


class HomeActivity : LFGActivity(), HomeContract.View {
    private val viewPagerAdapter = BottomNavViewPagerAdapter(this)

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setupAds()
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

    enum class AdType {
        TWITTER,
        TWITCH,
        TRACKER
    }

    private fun setupAds() {
        // randomizer
//        val randomInteger = try {
//            Random.nextInt(1, 100)
//        } catch (e: ArithmeticException) {
//            0
//        }
        val randomInteger = Random.nextInt(1, 100)
        if (randomInteger % 2 == 0) {
            showAd()
        } else {
            hideAd()
        }
    }

    private fun showAd() {
        val list = listOf<AdType>(AdType.TWITTER, AdType.TWITCH, AdType.TRACKER)
        val randomInteger = Random.nextInt(0, 3)
        when (list[randomInteger]) {
            AdType.TWITTER -> {
                binding.ivBanner.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable._70x250_twitter
                    )
                )
                binding.ivBanner.setOnClickWithDelay {
                    navigateToTwitter()
                }
                binding.clAd.visibility = View.VISIBLE
                binding.btnCloseAd.visibility = View.VISIBLE
            }
            AdType.TWITCH -> {
                binding.ivBanner.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable._70x250_twitch
                    )
                )
                binding.clAd.visibility = View.VISIBLE
                binding.ivBanner.setOnClickWithDelay {
                    navigateToTwitch()
                }
                binding.btnCloseAd.visibility = View.VISIBLE
            }
            AdType.TRACKER -> {
                binding.ivBanner.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable._70x250_tracker
                    )
                )
                binding.ivBanner.setOnClickWithDelay {
                    navigateToTracker()
                }
                binding.clAd.visibility = View.VISIBLE
                binding.btnCloseAd.visibility = View.VISIBLE
            }
        }

        binding.btnCloseAd.setOnClickWithDelay {
            binding.clAd.visibility = View.GONE
        }
    }

    private fun hideAd() {
        binding.clAd.visibility = View.GONE
    }

    private fun navigateToTwitch() {
        val uri: Uri = Uri.parse("https://www.twitch.com/_u/lfg4all")

        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("tv.twitch.android.viewer")

        try {
            startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.twitch.com/lfg4all")
                )
            )
        }
    }

    private fun navigateToTwitter() {
        val uri: Uri = Uri.parse("twitter://user?screen_name=_Louiserin_")

        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("tv.twitch.android.viewer")

        try {
            startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/_Louiserin_")
                )
            )
        }
    }

    private fun navigateToTracker() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://lfg4all.com")
            )
        )
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