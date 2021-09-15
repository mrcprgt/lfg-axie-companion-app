package com.lfgcompany.lfgaxiecompanionapp.app.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LocalUser(

    @PrimaryKey
    @ColumnInfo(name = "ronin")
    val ronin: String,

    @ColumnInfo(name = "manager_share")
    val managerShare: Int,

    @ColumnInfo(name = "scholar_share")
    val scholarShare: Int,

    @ColumnInfo(name = "initial_slp")
    val initialSlp: Int
)