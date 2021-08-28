package com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord

import com.mrcprgt.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.toJavaDateFromUTC
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.Repository
import javax.inject.Inject

class SlpRecordRepository @Inject constructor(
    private val dao: LocalSlpRecordDao,
): Repository(), SlpRecordGateway{
    override suspend fun saveRecordForToday(data: SlpRecord) {
        dao.save(
            LocalSlpRecord(
            date = data.date.toString(),
            amount = data.amount
        ))
    }

    override suspend fun getRecords(offset: Int): List<SlpRecord> {
        return dao.get().map{
            SlpRecord(
                it.date.toJavaDateFromUTC(),
                it.amount
            )
        }
    }

    override suspend fun getDailyTotals(): Int {
        return dao.getAverage()
    }

    override suspend fun getWeeklyTotals(): Int {
        val list = dao.get()
        var total = 0
        list.forEach {
            total += it.amount
        }
        return total / 7
    }

    override suspend fun getMonthlyTotals(): Int {
        val list = dao.get()
        var total = 0
        list.forEach {
            total += it.amount
        }
        return total / 30
    }
}