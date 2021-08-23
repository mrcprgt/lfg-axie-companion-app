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
    this.scholarAddress,
    "",
    lastClaimAmount,
    lastClaimTimeStamp.toString(),
    0,
    this.inGameSlp,
    this.arenaRank,
    this.mmr,
    this.totalMatches,
    this.winRate.toInt(),
    this.ign
)