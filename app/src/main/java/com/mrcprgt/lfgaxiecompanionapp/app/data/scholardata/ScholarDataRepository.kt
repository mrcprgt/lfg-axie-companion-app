package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.mrcprgt.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.Repository
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.ServiceHost
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.ServiceOption
import javax.inject.Inject

class ScholarDataRepository @Inject constructor(
    private val dao: ScholarDataDao
): Repository() , ScholarDataGateway {
//
//    private fun buildService() : ServiceOption {
//        val host= ServiceHost("https://api.lunaciarover.com/")
//        val client = ServiceClient.AB
//        return ServiceOption.Builder(
//           httpUrl =  "https://api.lunaciarover.com/",
//
//        )
//    }


    override suspend fun fetchScholarData(ronin: String): ScholarData {
//        val service = provideService<ScholarEndpoints>(
//            key= "scholar",
//
//        )
        TODO()
    }

    override suspend fun get(): ScholarData {
        TODO("Not yet implemented")
    }

    override suspend fun save(scholarData: ScholarData) {
        TODO("Not yet implemented")
    }
}