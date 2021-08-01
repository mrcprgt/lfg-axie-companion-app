package com.mrcprgt.lfgaxiecompanionapp.di

import android.app.Application
import com.mrcprgt.lfgaxiecompanionapp.LFGApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        AndroidModule::class,
        DataModule::class,
    ]
)
@Singleton
interface AppComponent : AndroidInjector<LFGApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
