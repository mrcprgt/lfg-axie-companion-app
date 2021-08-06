package com.mrcprgt.lfgaxiecompanionapp.di

import android.content.Context
import com.mrcprgt.lfgaxiecompanionapp.app.data.LFGDatabase
import com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata.FakeScholarDataRepository
import com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata.ScholarDataDao
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.AuthenticationRepository
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.FakeAuthenticationRepository
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.UserDao
import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class DataModule {

    @Binds
    abstract fun bindUserGateway(
        repo: FakeAuthenticationRepository
    ): AuthenticationGateway

    @Binds
    abstract fun bindScholarDataGateway(
        repo: FakeScholarDataRepository
    ): ScholarDataGateway

    companion object {

        @Provides
        fun provideDatabase(context: Context): LFGDatabase {
            return LFGDatabase.getInstance(context)
        }

        @Provides
        fun provideUserDao(database: LFGDatabase): UserDao = database.getUserDao()

        @Provides
        fun provideScholarDao(database: LFGDatabase): ScholarDataDao = database.getScholarDataDao()
    }
}
