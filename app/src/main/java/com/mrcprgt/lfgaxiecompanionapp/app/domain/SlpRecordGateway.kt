package com.mrcprgt.lfgaxiecompanionapp.app.domain

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord

interface SlpRecordGateway {
    suspend fun saveRecordForToday(data: SlpRecord)
    suspend fun getRecords(offset: Int): List<SlpRecord>

    suspend fun getDailyTotals(): Int
    suspend fun getWeeklyTotals(): Int
    suspend fun getMonthlyTotals(): Int
}