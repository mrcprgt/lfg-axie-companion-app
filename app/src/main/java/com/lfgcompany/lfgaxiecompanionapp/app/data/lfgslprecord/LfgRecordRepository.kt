package com.lfgcompany.lfgaxiecompanionapp.app.data.lfgslprecord

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.lfgcompany.lfgaxiecompanionapp.app.data.lfgslprecord.LfgRecordMapper.toDomain
import com.lfgcompany.lfgaxiecompanionapp.app.data.lfgslprecord.LfgRecordMapper.toLocal
import com.lfgcompany.lfgaxiecompanionapp.app.data.scholardata.RemoteLFGRecordDeserializer
import com.lfgcompany.lfgaxiecompanionapp.app.data.user.UserDao
import com.lfgcompany.lfgaxiecompanionapp.app.domain.LFGSlpRecordAndGainsGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException
import com.lfgcompany.lfgaxiecompanionapp.tools.repository.Repository
import com.lfgcompany.lfgaxiecompanionapp.tools.repository.ServiceHost
import com.lfgcompany.lfgaxiecompanionapp.tools.repository.ServiceOption
import com.lfgcompany.lfgaxiecompanionapp.tools.repository.process
import retrofit2.converter.gson.GsonConverterFactory
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
//            response!!.forEach {
//                dao.save(
//                    it.toDomain().toLocal()
//                )
//            }
            response!!.map {
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

    override suspend fun getDailyTotals(): Int {
        return dao.getAverage()
    }

    override suspend fun getWeeklyTotals(): Int {
        return dao.getGains() / 7
    }

    override suspend fun getMonthlyTotals(): Int {
        return dao.getGains() / 30
    }
}