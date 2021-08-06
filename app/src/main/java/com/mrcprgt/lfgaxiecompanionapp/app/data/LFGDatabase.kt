package com.mrcprgt.lfgaxiecompanionapp.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata.LocalScholarData
import com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata.ScholarDataDao
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.UserDao
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.LocalUser

@Database(
    entities = [
        LocalUser::class,
        LocalScholarData::class,
    ],
    version = 2,
    exportSchema = false
)

// Insert type converters here

abstract class LFGDatabase : RoomDatabase() {
    // Get Dao
    abstract fun getUserDao(): UserDao
    abstract fun getScholarDataDao(): ScholarDataDao

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
