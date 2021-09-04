package com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord

import com.mrcprgt.lfgaxiecompanionapp.tools.repository.RetrofitResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LfgRecordEndpoints {

    @GET("/gains/{ronin}")
    suspend fun fetch(
        @Path("ronin") ronin: String
    ): RetrofitResponse<RemoteLfgRecord>
}