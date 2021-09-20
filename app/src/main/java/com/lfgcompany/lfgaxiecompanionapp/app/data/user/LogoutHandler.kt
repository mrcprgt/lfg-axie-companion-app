package com.lfgcompany.lfgaxiecompanionapp.app.data.user

import android.content.Context
import com.lfgcompany.lfgaxiecompanionapp.app.data.LFGDatabase
import com.lfgcompany.lfgaxiecompanionapp.app.domain.LogoutGateway
import javax.inject.Inject

class LogoutHandler @Inject constructor(
    private val context: Context
) : LogoutGateway {
    override fun logout() {
        LFGDatabase.getInstance(context)
            .clearAllTables()
    }
}
