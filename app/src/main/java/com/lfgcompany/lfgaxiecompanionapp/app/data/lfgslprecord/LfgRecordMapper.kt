package com.lfgcompany.lfgaxiecompanionapp.app.data.lfgslprecord

import android.util.Log
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.epochToDate
import java.util.*

object LfgRecordMapper {
    fun RemoteLfgRecord.RemoteRecord.toDomain() = LFGSlpRecordAndGains(
        date = Date(this.date.toLong()),
        gains = this.gained,
        total = this.total
    )

    fun LFGSlpRecordAndGains.toLocal(): LocalLfgRecord {
        Log.e("toLocal", this.date.time.toString())

        return LocalLfgRecord(
            date = this.date.time.toString(),
            gain = this.gains,
            total = this.total
        )
    }

    fun LocalLfgRecord.toDomain() = LFGSlpRecordAndGains(
        date = epochToDate(this.date),
        gains = this.gain,
        total = this.total
    )
}