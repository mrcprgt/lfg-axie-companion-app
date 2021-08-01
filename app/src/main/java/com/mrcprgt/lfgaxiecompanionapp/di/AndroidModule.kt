package com.mrcprgt.lfgaxiecompanionapp.di

import com.mrcprgt.lfgaxiecompanionapp.app.presentation.login.LoginActivity
import com.mrcprgt.lfgaxiecompanionapp.tools.scopes.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity


}
