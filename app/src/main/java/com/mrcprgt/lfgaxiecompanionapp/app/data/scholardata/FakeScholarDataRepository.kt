package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.mrcprgt.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import kotlinx.coroutines.delay
import java.util.*

class FakeScholarDataRepository : ScholarDataGateway{
    override suspend fun fetchScholarData(ronin: String): ScholarData {
        return ScholarData(
            "0xsadad",
            Date(),
            1200,
            Date(),
            5000,
            2500,
            1000,
            1500,
            50,
            50,
            "Scholar 1"
        )
    }

    override suspend fun get(): ScholarData {
        return ScholarData(
            "0xsadad",
            Date(),
            1200,
            Date(),
            5000,
            2500,
            1000,
            1500,
            50,
            50,
            "Scholar 1"
        )
    }

    override suspend fun save(scholarData: ScholarData) {
        delay(2000)
    }
}