package com.mrcprgt.lfgaxiecompanionapp

import android.app.Application
import com.mrcprgt.lfgaxiecompanionapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class LFGApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()

        startDagger()

//        startLifecycleCallbackChecks()
    }

    private fun startDagger() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

//    private fun startLifecycleCallbackChecks() {
//        registerActivityLifecycleCallbacks(topActivityProvider)
//    }
}
