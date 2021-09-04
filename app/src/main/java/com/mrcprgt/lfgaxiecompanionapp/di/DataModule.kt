package com.mrcprgt.lfgaxiecompanionapp.di

import android.content.Context
import com.mrcprgt.lfgaxiecompanionapp.app.data.LFGDatabase
import com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord.LfgRecordRepository
import com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord.LocalLfgRecordDao
import com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata.ScholarDataDao
import com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata.ScholarDataRepository
import com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord.FakeSlpRecordRepository
import com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord.LocalSlpRecordDao
import com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord.SlpRecordRepository
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.AuthenticationRepository
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.UserDao
import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.LFGSlpRecordAndGainsGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class DataModule {

    @Binds
    abstract fun bindUserGateway(
        repo: AuthenticationRepository
    ): AuthenticationGateway

    @Binds
    abstract fun bindScholarDataGateway(
        repo: ScholarDataRepository
    ): ScholarDataGateway

    @Binds
    abstract fun bindSlpRecordGateway(
        repo: SlpRecordRepository
    ): SlpRecordGateway

    @Binds
    abstract fun bindLfgSlpRecordGateway(
        repo: LfgRecordRepository
    ): LFGSlpRecordAndGainsGateway

    companion object {
        @Provides
        fun provideDatabase(context: Context): LFGDatabase {
            return LFGDatabase.getInstance(context)
        }

        @Provides
        fun provideUserDao(database: LFGDatabase): UserDao = database.getUserDao()

        @Provides
        fun provideScholarDao(database: LFGDatabase): ScholarDataDao = database.getScholarDataDao()

        @Provides
        fun provideSlpRecordDao(database: LFGDatabase): LocalSlpRecordDao =
            database.getSlpRecordDao()

        @Provides
        fun provideLfgRecordDao(database: LFGDatabase): LocalLfgRecordDao =
            database.getLfgRecordDao()
    }
}
