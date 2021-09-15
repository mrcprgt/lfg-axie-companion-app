package com.lfgcompany.lfgaxiecompanionapp.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lfgcompany.lfgaxiecompanionapp.app.data.lfgslprecord.LocalLfgRecord
import com.lfgcompany.lfgaxiecompanionapp.app.data.lfgslprecord.LocalLfgRecordDao
import com.lfgcompany.lfgaxiecompanionapp.app.data.scholardata.LocalScholarData
import com.lfgcompany.lfgaxiecompanionapp.app.data.scholardata.ScholarDataDao
import com.lfgcompany.lfgaxiecompanionapp.app.data.slprecord.LocalSlpRecord
import com.lfgcompany.lfgaxiecompanionapp.app.data.slprecord.LocalSlpRecordDao
import com.lfgcompany.lfgaxiecompanionapp.app.data.user.LocalUser
import com.lfgcompany.lfgaxiecompanionapp.app.data.user.UserDao

@Database(
    entities = [
        LocalUser::class,
        LocalSlpRecord::class,
        LocalScholarData::class,
        LocalLfgRecord::class,
    ],
    version = 8,
    exportSchema = false
)

// Insert type converters here

abstract class LFGDatabase : RoomDatabase() {
    // Get Dao
    abstract fun getUserDao(): UserDao
    abstract fun getScholarDataDao(): ScholarDataDao
    abstract fun getSlpRecordDao(): LocalSlpRecordDao
    abstract fun getLfgRecordDao(): LocalLfgRecordDao

    companion object {
        private const val DATABASE_NAME = "lfg.db"

        @Volatile
        private var instance: LFGDatabase? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): LFGDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context, LFGDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            return instance!!
        }
    }
}
