package com.lfgcompany.lfgaxiecompanionapp.app.data.pvprecord

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pvp_record")
data class LocalPvpRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "result")
    val result: String,

    @ColumnInfo(name = "slp_earned")
    val slpEarned: Int
)