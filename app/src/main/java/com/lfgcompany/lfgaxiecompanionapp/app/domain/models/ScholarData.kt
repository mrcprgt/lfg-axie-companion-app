package com.lfgcompany.lfgaxiecompanionapp.app.domain.models

import java.util.*

data class ScholarData(
    val scholarAddress: String,
    val lastClaimAmount: Int,
    val lastClaimTimeStamp: Date,
    val inGameSlp: Int,
    val arenaRank: Int,
    val mmr: Int,
    val totalMatches: Int,
    val wins: Int,
    val loses: Int,
    val draws: Int,
    val winRate: Double,
    val ign: String,
)
