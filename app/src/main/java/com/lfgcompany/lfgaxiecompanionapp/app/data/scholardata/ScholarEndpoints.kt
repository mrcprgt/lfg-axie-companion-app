package com.lfgcompany.lfgaxiecompanionapp.app.data.scholardata

import com.lfgcompany.lfgaxiecompanionapp.tools.repository.RetrofitResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ScholarEndpoints{
    @GET("clients/{ronin}/items/1")
//    @GET("https://https://game-api.skymavis.com/game-api/clients/{ronin}/items/1")
    suspend fun fetchStats(
        @Path("ronin") ronin: String
    ): RetrofitResponse<RemoteProfile>

//    @GET("https://https://game-api.skymavis.com/game-api/last-season-leaderboard/")
    @GET("last-season-leaderboard")
    suspend fun fetchPVP(
        @QueryMap params: Map<String, String>
    ): RetrofitResponse<RemotePvPProfile>
}