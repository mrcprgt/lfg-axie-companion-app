package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.google.gson.annotations.SerializedName

data class RemoteProfile(
    @SerializedName("client_id")
    val success: String,

    @SerializedName("total")
    val total: Int,

    @SerializedName("blockchain_related")
    val blockChainRelated: RemoteBlockChainRelated,

    @SerializedName("last_claimed_item_at")
    val lastClaimTimeStamp: Int,

    ){
    data class RemoteBlockChainRelated(
        @SerializedName("checkpoint")
        val lifetimeSlp: Int,
    )

}
