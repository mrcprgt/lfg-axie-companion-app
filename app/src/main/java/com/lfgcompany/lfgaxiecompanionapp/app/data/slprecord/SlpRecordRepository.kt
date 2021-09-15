package com.lfgcompany.lfgaxiecompanionapp.app.data.slprecord

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.lfgcompany.lfgaxiecompanionapp.app.data.scholardata.RemoteLFGResponseDeserializer
import com.lfgcompany.lfgaxiecompanionapp.app.data.user.UserDao
import com.lfgcompany.lfgaxiecompanionapp.app.domain.SlpRecordGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.parseUTCDate
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.toUTC
import com.lfgcompany.lfgaxiecompanionapp.tools.repository.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class SlpRecordRepository @Inject constructor(
    private val dao: LocalSlpRecordDao,
    private val userDao: UserDao
) : Repository(), SlpRecordGateway {

    private fun buildService(): ServiceOption {
        val host = ServiceHost("https://lfg-api.com")
        return ServiceOption.Builder(
            host,
        )
            .build()
    }

    override suspend fun saveRecordForToday(data: SlpRecord) {
        dao.save(
            LocalSlpRecord(
                date = data.date.toUTC(),
                amount = data.amount
            )
        )
    }

    override suspend fun getRecords(offset: Int): List<SlpRecord> {
        return dao.get().map {
            SlpRecord(
                it.date.parseUTCDate(),
                it.amount
            )
        }
    }

    override suspend fun syncInGameSlp(slp: Int) {
        try {
            val service = provideService<SlpRecordEndpoints>(
                key = "lfg",
                buildService()
            ) {
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(
                            object : TypeToken<RemoteSlpResponse>() {}.type,
                            RemoteLFGResponseDeserializer()
                        )
                        .setLenient()
                        .create()
                )
            }
            val response = service.putSlp(slp, userDao.get()!!.ronin).process()
            Log.e("RESPONSE", response.toString())

            if (!response.success) {
                throw LFGException("Failed to fetch from LFG API.")
            }
        } catch (e: LFGException) {
            throw e
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