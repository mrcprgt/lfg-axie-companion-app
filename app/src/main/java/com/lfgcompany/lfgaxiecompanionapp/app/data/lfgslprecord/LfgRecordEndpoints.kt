package com.lfgcompany.lfgaxiecompanionapp.app.data.lfgslprecord

import com.google.gson.annotations.SerializedName
import com.lfgcompany.lfgaxiecompanionapp.tools.repository.RetrofitResponse
import retrofit2.http.GET
import retrofit2.http.Path

data class LfgRecordBody(
    val data: BodyData
) {
    data class BodyData(
        @SerializedName("data")
        val data: MutableMap<String, String>
    )
}

interface LfgRecordEndpoints {
    @GET("/gains/{ronin}")
    suspend fun fetch(
        @Path("ronin") ronin: String,
    ): RetrofitResponse<RemoteLfgRecord>
}