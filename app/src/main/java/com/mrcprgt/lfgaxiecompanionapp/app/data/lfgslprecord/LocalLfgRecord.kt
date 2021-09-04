package com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lfg_record")
data class LocalLfgRecord(
    @PrimaryKey
    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "gain")
    val gain: Int,

    @ColumnInfo(name = "total")
    val total: Int
)