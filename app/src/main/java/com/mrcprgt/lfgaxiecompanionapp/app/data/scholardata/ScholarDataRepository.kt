package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mrcprgt.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.epochToDate
import com.mrcprgt.lfgaxiecompanionapp.tools.repository.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ScholarDataRepository @Inject constructor(
    private val dao: ScholarDataDao
) : Repository(), ScholarDataGateway {

    private fun buildService(): ServiceOption {
        val host = ServiceHost("https://game-api.skymavis.com/game-api/")
        return ServiceOption.Builder(
            host,
        ).build()
    }


    override suspend fun fetchScholarData(ronin: String): ScholarData {
        val service = provideService<ScholarEndpoints>(
            key = "scholar",
            buildService()
        ) {
            GsonConverterFactory.create(
                GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(
                        object : TypeToken<RemoteScholarData>() {}.type,
                        ScholarDataDeserializer()
                    )
                    .registerTypeAdapter(
                        object : TypeToken<RemoteProfile>() {}.type,
                        RemoteProfileDeserializer()
                    )
                    .registerTypeAdapter(
                        object : TypeToken<RemotePvpProfileDeserializer>() {}.type,
                        RemotePvpProfileDeserializer()
                    )
                    .setLenient()
                    .create()
            )
        }
        val stats = service.fetchStats(ronin).process()
        val query = RequestParameterHelper()
            .addParam("limit", "0")
            .addParam("offset", "0")
            .addParam("client_id", ronin)
            .build()
        val pvp = service.fetchPVP(query).process()
        return ScholarData(
            scholarAddress = ronin,
            lastClaimAmount = stats.blockChainRelated.lifetimeSlp,
            lastClaimTimeStamp = epochToDate(stats.lastClaimTimeStamp.toString()),
            inGameSlp = stats.total,
            arenaRank = pvp.items[1].rank,
            mmr = pvp.items[1].mmr,
            totalMatches = 0,
            winRate = 0.0,
            ign = pvp.items[1].name
        )
    }

    override suspend fun get(): ScholarData {
        TODO("Not yet implemented")
    }

    override suspend fun save(scholarData: ScholarData) {
        dao.save(scholarData.toLocal())
    }
}