package com.mrcprgt.lfgaxiecompanionapp.di

import android.app.Application
import android.content.Context
import com.mrcprgt.lfgaxiecompanionapp.tools.CoroutineScopeProvider
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
