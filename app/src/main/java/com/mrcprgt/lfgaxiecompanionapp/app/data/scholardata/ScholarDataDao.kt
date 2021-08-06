package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScholarDataDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(scholarData: LocalScholarData)

    @Query("SELECT * FROM scholar_data LIMIT 1")
    fun get(): LocalScholarData

    @Query("DELETE FROM scholar_data")
    fun clear()
}