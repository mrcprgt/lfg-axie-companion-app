package com.mrcprgt.lfgaxiecompanionapp.app.domain.models

import java.util.*

data class ScholarData(
    val scholarAddress: String,
    val updatedAt: Date,
    val lastClaimAmount: Int,
    val lastClaimTimeStamp: Date,
    val totalSlp: Int,
    val inGameSlp: Int,
    val arenaRank: Int,
    val mmr: Int,
    val totalMatches: Int,
    val winRate: Int,
    val ign: String,
)
