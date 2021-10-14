package com.lfgcompany.lfgaxiecompanionapp.app.data.pvprecord

import com.lfgcompany.lfgaxiecompanionapp.app.data.pvprecord.PvpRecordMapper.toDomain
import com.lfgcompany.lfgaxiecompanionapp.app.data.pvprecord.PvpRecordMapper.toLocal
import com.lfgcompany.lfgaxiecompanionapp.app.domain.PvpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import javax.inject.Inject

class PvpRecordRepository @Inject constructor(
    private val dao: LocalPvpRecordDao
) : PvpRecordGateway {

    override suspend fun addRecord(pvpRecord: PvpRecord) {
        dao.save(pvpRecord.toLocal())
    }

    override suspend fun getRecords(): List<PvpRecord> {
        return dao.getAllRecords().map {
            it.toDomain()
        }
    }

    override suspend fun clearRecords() {
        dao.delete()
    }

    override suspend fun getWins(): Int {
        return dao.get("win")
    }

    override suspend fun getLoses(): Int {
        return dao.get("lose")
    }

    override suspend fun getDraws(): Int {
        return dao.get("draw")
    }

    override suspend fun getTotalMatches(): Int {
        return dao.getTotalMatches()
    }
}