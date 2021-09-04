package com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord.LocalSlpRecord

@Dao
interface LocalLfgRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(record: LocalLfgRecord)

    @Query("SELECT * FROM lfg_record ORDER BY date ASC")
    fun get(): List<LocalLfgRecord>

    @Query("Select * From lfg_record ORDER BY date ASC LIMIT 10 OFFSET :offset")
    fun get(offset: Int): List<LocalLfgRecord>

    @Query("SELECT AVG(gain) FROM lfg_record")
    fun getAverage(): Int

    @Query("DELETE FROM lfg_record")
    fun clear()
}