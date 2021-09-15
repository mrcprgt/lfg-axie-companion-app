package com.lfgcompany.lfgaxiecompanionapp.app.data.slprecord

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalSlpRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(slpRecord: LocalSlpRecord)

    @Query("SELECT * FROM slp_record ORDER BY date ASC")
    fun get(): List<LocalSlpRecord>

    @Query("SELECT AVG(amount) FROM slp_record")
    fun getAverage(): Int

    @Query("DELETE FROM slp_record")
    fun clear()
}