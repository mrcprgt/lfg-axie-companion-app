package com.lfgcompany.lfgaxiecompanionapp.app.data.lfgslprecord

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalLfgRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(record: LocalLfgRecord)

    @Query("SELECT * FROM lfg_record ORDER BY date DESC")
    fun get(): List<LocalLfgRecord>

    @Query("Select * From lfg_record ORDER BY date DESC LIMIT 10 OFFSET :offset")
    fun get(offset: Int): List<LocalLfgRecord>

    @Query("SELECT AVG(gain) FROM lfg_record")
    fun getAverage(): Int

    @Query("SELECT SUM(gain) FROM lfg_record")
    fun getGains(): Int

    @Query("DELETE FROM lfg_record")
    fun clear()
}