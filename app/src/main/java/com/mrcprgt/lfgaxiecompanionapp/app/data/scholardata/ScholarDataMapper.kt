package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.epochToDate


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
    winRate = this.winRate,
    ign = this.ign,
    wins = this.wins,
    draws = this.draws,
    loses = this.loses
)

fun LocalScholarData.toDomain() = ScholarData(
    scholarAddress = this.ronin,
    lastClaimAmount = lastClaimAmount,
    lastClaimTimeStamp = epochToDate(this.lastClaimTimeStamp),
    inGameSlp = this.inGameSlp,
    arenaRank = this.arenaRank,
    mmr = this.mmr,
    totalMatches = totalMatches,
    winRate = this.winRate,
    ign = this.ign,
    loses = this.loses,
    draws = this.draws,
    wins = this.wins
)