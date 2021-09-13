package com.mrcprgt.lfgaxiecompanionapp.app.domain

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains

interface LFGSlpRecordAndGainsGateway {
    suspend fun fetchRecordsAndGains(ronin: String): List<LFGSlpRecordAndGains>
    suspend fun save(list: List<LFGSlpRecordAndGains>)
    suspend fun get(offset: Int): List<LFGSlpRecordAndGains>

    suspend fun getDailyTotals(): Int
    suspend fun getWeeklyTotals(): Int
    suspend fun getMonthlyTotals(): Int
}