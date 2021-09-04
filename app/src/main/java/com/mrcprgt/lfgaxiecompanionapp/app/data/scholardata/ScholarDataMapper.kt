package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.epochToDate
import java.util.*


fun RemoteScholarData.toDomain() = ScholarData(
    this.roninAddress,
    this.lastClaimAmount.toInt(),
    epochToDate(this.lastClaimTimeStamp),
    this.totalSlp,
    this.inGameSlp,
    this.rank,
    this.mmr,
    this.winRate.toDouble(),
    this.ign ?: ""
)

fun ScholarData.toLocal() = LocalScholarData(
    ronin = this.scholarAddress,
    updatedAt = "",
    lastClaimAmount = lastClaimAmount,
    lastClaimTimeStamp = lastClaimTimeStamp.time.toString(),
    totalSlp = 0,
    inGameSlp = this.inGameSlp,
    arenaRank = this.arenaRank,
    mmr = this.mmr,
    totalMatches = this.totalMatches,
    winRate = this.winRate.toInt(),
    ign = this.ign
)

fun LocalScholarData.toDomain() = ScholarData(
    scholarAddress = this.ronin,
    lastClaimAmount = lastClaimAmount,
    lastClaimTimeStamp = epochToDate(this.lastClaimTimeStamp),
    inGameSlp = this.inGameSlp,
    arenaRank = this.arenaRank,
    mmr = this.mmr,
    totalMatches = 0,
    winRate = 0.0,
    ign = this.ign
)