package com.lfgcompany.lfgaxiecompanionapp.app.data.slprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.SlpRecord
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

class FakeSlpRecordRepository @Inject constructor() : SlpRecordGateway {
    override suspend fun saveRecordForToday(data: SlpRecord) {
        delay(5000)
    }

    override suspend fun getRecords(offset: Int): List<SlpRecord> {
        delay(5000)
        return Array(50) {
            SlpRecord(
                Date(),
                500
            )
        }.toList()
    }

    override suspend fun syncInGameSlp(slp: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getDailyTotals(): Int {
        return 500
    }

    override suspend fun getWeeklyTotals(): Int {
        return 230
    }

    override suspend fun getMonthlyTotals(): Int {
        return 3000
    }
}