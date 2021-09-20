package com.lfgcompany.lfgaxiecompanionapp.di

import com.lfgcompany.lfgaxiecompanionapp.app.presentation.buffs.BuffsFragment
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.gains.GainsFragment
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.home.HomeActivity
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.login.LoginActivity
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy.EnergyCounterDialog
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy.PvpBuddyFragment
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.slprecord.AddRecordDialog
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.slprecord.SlpRecordFragment
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.splash.SplashActivity
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.stats.MenuBottomSheet
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.stats.StatsFragment
import com.lfgcompany.lfgaxiecompanionapp.tools.scopes.ActivityScoped
import com.lfgcompany.lfgaxiecompanionapp.tools.scopes.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeStatsFragment(): StatsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeGainsFragment(): GainsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributePvpBuddyFragment(): PvpBuddyFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeBuffsFragment(): BuffsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeSlpRecordFragment(): SlpRecordFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeAddRecordDialog(): AddRecordDialog

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeEnergyCounterDialog(): EnergyCounterDialog

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeMenuBottomSheet(): MenuBottomSheet
}
