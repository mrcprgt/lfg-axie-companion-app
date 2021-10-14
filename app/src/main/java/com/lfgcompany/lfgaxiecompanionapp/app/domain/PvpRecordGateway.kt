package com.lfgcompany.lfgaxiecompanionapp.app.domain

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord

interface PvpRecordGateway {
    suspend fun addRecord(pvpRecord: PvpRecord)
    suspend fun getRecords(): List<PvpRecord>
    suspend fun clearRecords()
    suspend fun getWins(): Int
    suspend fun getLoses(): Int
    suspend fun getDraws(): Int
    suspend fun getTotalMatches(): Int
}