package com.mrcprgt.lfgaxiecompanionapp.di

import com.mrcprgt.lfgaxiecompanionapp.app.presentation.buffs.BuffsFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.gains.GainsFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.home.HomeActivity
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.login.LoginActivity
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.pvpbuddy.EnergyCounterDialog
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.pvpbuddy.PvpBuddyFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord.AddRecordDialog
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord.SlpRecordFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.stats.StatsFragment
import com.mrcprgt.lfgaxiecompanionapp.tools.scopes.ActivityScoped
import com.mrcprgt.lfgaxiecompanionapp.tools.scopes.FragmentScoped
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
}
