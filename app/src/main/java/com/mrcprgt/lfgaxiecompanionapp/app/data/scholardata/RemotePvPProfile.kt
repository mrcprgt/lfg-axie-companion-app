package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.google.gson.annotations.SerializedName

data class RemotePvPProfile(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("items")
    val items: List<RemotePvPStatsItem>
){
    data class RemotePvPStatsItem(
        @SerializedName("win_total")
        val wins: Int,

        @SerializedName("lose_total")
        val loses: Int,
        @SerializedName("draw_total")
        val draw: Int,
        @SerializedName("rank")
        val rank: Int,
        @SerializedName("elo")
        val mmr: Int,
        @SerializedName("name")
        val name: String,
    )

}