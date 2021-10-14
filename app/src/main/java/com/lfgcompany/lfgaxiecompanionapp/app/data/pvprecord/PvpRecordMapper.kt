package com.lfgcompany.lfgaxiecompanionapp.app.data.pvprecord

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.parseUTCDate
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.toUTC
import java.util.*

object PvpRecordMapper {

    fun PvpRecord.toLocal(): LocalPvpRecord {
        return LocalPvpRecord(
            id = this.id,
            date = this.date.toUTC(),
            result = this.pvpResult.name.lowercase(Locale.getDefault()),
            slpEarned = this.slpEarned
        )
    }

    fun LocalPvpRecord.toDomain(): PvpRecord {
        return PvpRecord(
            id = this.id,
            date = this.date.parseUTCDate(),
            pvpResult = this.result.toPvpResult(),
            slpEarned = this.slpEarned
        )
    }

    fun String.toPvpResult(): PvpRecord.PvpResult {
        return when {
            this.lowercase(Locale.getDefault()).contentEquals("win") -> PvpRecord.PvpResult.WIN
            this.lowercase(Locale.getDefault()).contentEquals("draw") -> PvpRecord.PvpResult.DRAW
            this.lowercase(Locale.getDefault()).contentEquals("lose") -> PvpRecord.PvpResult.LOSE
            else -> throw LFGException("Not a pvp status.")
        }
    }
}