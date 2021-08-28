package com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "slp_record")
data class LocalSlpRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "amount")
    val amount: Int,
)