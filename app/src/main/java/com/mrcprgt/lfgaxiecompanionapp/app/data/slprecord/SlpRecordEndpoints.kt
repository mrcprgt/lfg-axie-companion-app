package com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord

import com.mrcprgt.lfgaxiecompanionapp.tools.repository.RetrofitResponse
import retrofit2.http.*

interface SlpRecordEndpoints {
    @FormUrlEncoded
    @Headers("Authorization: Basic TEZHVGVhbTozTWMoTX46TFIrUFk3Y3N3")
    @PUT("/{ronin}")
    suspend fun putSlp(
        @Field("ClaimedSlp") slp: Int,
        @Path("ronin") ronin: String
    ): RetrofitResponse<RemoteSlpResponse>
}