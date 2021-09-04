package com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord

import com.google.gson.annotations.SerializedName

data class RemoteSlpResponse(
    @SerializedName("Success")
    val success: Boolean,

    @SerializedName("details")
    val details: RemoteDetails
){
    data class RemoteDetails(
        @SerializedName("ClaimedSlp")
        val claimedSlp: Int
    )
}

