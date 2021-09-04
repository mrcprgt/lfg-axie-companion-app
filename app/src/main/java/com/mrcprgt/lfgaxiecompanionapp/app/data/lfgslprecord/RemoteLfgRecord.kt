package com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord

import com.google.gson.annotations.SerializedName

data class RemoteLfgRecord(
    @SerializedName("Success")
    val success: Boolean,

    @SerializedName("data")
    val data: List<RemoteRecord>?

) {
    data class RemoteRecord(
        @SerializedName("Date")
        val date: String,

        @SerializedName("Gained")
        val gained: Int,

        @SerializedName("Total")
        val total: Int
    )
}
