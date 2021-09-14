package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scholar_data")
data class LocalScholarData(
    @PrimaryKey
    @ColumnInfo(name = "ronin")
    val ronin: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String,

    @ColumnInfo(name = "last_claim_amount")
    val lastClaimAmount: Int,

    @ColumnInfo(name = "last_claim_timestamp")
    val lastClaimTimeStamp: String,

    @ColumnInfo(name = "total_slp")
    val totalSlp: Int,

    @ColumnInfo(name = "in_game_slp")
    val inGameSlp: Int,

    @ColumnInfo(name = "arena_rank")
    val arenaRank: Int,

    @ColumnInfo(name = "mmr")
    val mmr: Int,

    @ColumnInfo(name = "total_matches")
    val totalMatches: Int,

    @ColumnInfo(name = "win_rate")
    val winRate: Double,

    @ColumnInfo(name = "wins")
    val wins: Int,

    @ColumnInfo(name = "draws")
    val draws: Int,

    @ColumnInfo(name = "loses")
    val loses: Int,

    @ColumnInfo(name = "ign")
    val ign: String,
)