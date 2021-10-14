package com.lfgcompany.lfgaxiecompanionapp.app.data.pvprecord

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalPvpRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(record: LocalPvpRecord)

    @Query("SELECT * FROM pvp_record ORDER BY date DESC")
    fun getAllRecords(): List<LocalPvpRecord>

    @Query("SELECT COUNT(*) FROM pvp_record")
    fun getTotalMatches(): Int

    @Query("SELECT COUNT(*) FROM pvp_record WHERE result = :result")
    fun get(result: String): Int

    @Query("DELETE FROM pvp_record")
    fun delete()
}