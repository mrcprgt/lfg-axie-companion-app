package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.mrcprgt.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject
//
//class FakeScholarDataRepository @Inject constructor() : ScholarDataGateway {
//    override suspend fun fetchScholarData(ronin: String): ScholarData {
//        delay(2000)
//        return ScholarData(
//            scholarAddress = "0xsadad",
//            updatedAt = Date(),
//            lastClaimAmount = 1200,
//            lastClaimTimeStamp = Date(),
//            totalSlp = 5000,
//            inGameSlp = 2500,
//            arenaRank = 1000,
//            mmr = 1500,
//            totalMatches = 50,
//            winRate = 50.0,
//            ign = "Scholar 1"
//        )
//    }
//
//    override suspend fun get(): ScholarData {
//        delay(2000)
//        return ScholarData(
//            scholarAddress = "0xsadad",
//            updatedAt = Date(),
//            lastClaimAmount = 1200,
//            lastClaimTimeStamp = Date(),
//            totalSlp = 5000,
//            inGameSlp = 2500,
//            arenaRank = 1000,
//            mmr = 1500,
//            totalMatches = 50,
//            winRate = 50.0,
//            ign = "Scholar 1"
//        )
//    }
//
//    override suspend fun save(scholarData: ScholarData) {
//        delay(2000)
//    }
//}