package com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord.LfgRecordMapper.toDomain
import com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord.LfgRecordMapper.toLocal
import com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata.RemoteLFGRecordDeserializer
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.UserDao
import com.mrcprgt.lfgaxiecompanionapp.app.domain.LFGSlpRecordAndGainsGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.mrcprgt.lfgaxiecompanionapp.tools.LFGException
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.Repository
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.ServiceHost
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.ServiceOption
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.process
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Inject

class LfgRecordRepository @Inject constructor(
    private val dao: LocalLfgRecordDao,
    private val userDao: UserDao
) : Repository(), LFGSlpRecordAndGainsGateway {
    private fun buildService(): ServiceOption {
        val host = ServiceHost("https://lfg-api.com")
        return ServiceOption.Builder(
            host
        ).build()
    }

    override suspend fun fetchRecordsAndGains(ronin: String): List<LFGSlpRecordAndGains> {
        return try {
            val service = provideService<LfgRecordEndpoints>(
                key = "lfg_record",
                buildService()
            ) {
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(
                            object : TypeToken<RemoteLfgRecord>() {}.type,
                            RemoteLFGRecordDeserializer()
                        )
                        .setLenient()
                        .create()
                )
            }

            val response = service.fetch(userDao.get()!!.ronin).process().data
            response!!.forEach {
                dao.save(
                    it.toDomain().toLocal()
                )
            }
            response.map {
                it.toDomain()
            }
        } catch (e: LFGException) {
            throw e
        }
    }

    override suspend fun save(list: List<LFGSlpRecordAndGains>) {
        list.forEach {
            dao.save(it.toLocal())

        }
    }

    override suspend fun get(offset: Int): List<LFGSlpRecordAndGains> {
        return dao.get(offset).map { it.toDomain() }
    }
}