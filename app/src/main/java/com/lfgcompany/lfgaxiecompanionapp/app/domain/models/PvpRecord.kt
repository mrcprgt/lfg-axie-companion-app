package com.lfgcompany.lfgaxiecompanionapp.app.domain.models

import java.util.*

data class PvpRecord(
    val id: Int,
    val pvpResult: PvpResult,
    val date: Date,
    val slpEarned: Int
) {
    enum class PvpResult {
        WIN,
        DRAW,
        LOSE
    }
}