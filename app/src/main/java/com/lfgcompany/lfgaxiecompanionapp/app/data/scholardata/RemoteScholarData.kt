package com.lfgcompany.lfgaxiecompanionapp.app.data.scholardata

import com.google.gson.annotations.SerializedName

data class RemoteScholarData(
    @SerializedName("ronin_address")
    val roninAddress: String,

    @SerializedName("updated_on")
    val lastUpdatedAt: String,

    @SerializedName("last_claim_amount")
    val lastClaimAmount: String,

    @SerializedName("last_claim_timestamp")
    val lastClaimTimeStamp: String,

    @SerializedName("ronin_slp")
    val roninSlp: Int,

    @SerializedName("total_slp")
    val totalSlp: Int,

    @SerializedName("in_game_slp")
    val inGameSlp: Int,

    @SerializedName("slp_success")
    val slpSuccess: Boolean,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("mmr")
    val mmr: Int,

    @SerializedName("total_matches")
    val totalMatches: Int,

    @SerializedName("win_rate")
    val winRate: String,

    @SerializedName("ign")
    val ign: String?,

    @SerializedName("game_stats_success")
    val gameStatsSuccess: String,
)