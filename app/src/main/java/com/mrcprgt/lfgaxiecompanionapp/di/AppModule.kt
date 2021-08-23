package com.mrcprgt.lfgaxiecompanionapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.Settings
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.DefaultInteractorHandler
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideAppContext(app: Application): Context {
            return app
        }

        @Provides
        fun provideCoroutineScopeProvider(): CoroutineScopeProvider {
            return CoroutineScopeProvider()
        }

        @Provides
        fun provideInteractorHandler(
            handler: DefaultInteractorHandler
        ): InteractorHandler {
            return handler
        }

        @Provides
        fun provideSharedPrefs(context: Context): SharedPreferences {
            return context.getSharedPreferences(
                "LFG",
                Context.MODE_PRIVATE
            )
        }

        @Provides
        fun provideSettings(sharedPreferences: SharedPreferences): Settings {
            return Settings(sharedPreferences)
        }
//        @Provides
//        @Singleton
//        fun provideImageLoader(
//            context: Context
//        ): ImageLoader {
//            val builder = PicassoImageLoader.Builder(context)
//                .setEnableLogging(false)
//                .addInterceptor(CourierInterceptor(context))
//                .setCacheSize(Int.MAX_VALUE)
//                .setDoUnsafe(true)
//
//            return builder.build()
//        }

    }
}
