package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.mrcprgt.lfgaxiecompanionapp.tools.repository.RetrofitResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ScholarEndpoints{
    @GET("https://api.lunaciarover.com/stats/{ronin}")
    suspend fun fetchStats(@Path("ronin") ronin: String): RetrofitResponse<RemoteScholarData>
}