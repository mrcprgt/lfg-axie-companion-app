package com.lfgcompany.lfgaxiecompanionapp.app.domain

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.ScholarData

interface ScholarDataGateway {
    suspend fun fetchScholarData(ronin: String) : ScholarData
    suspend fun get(): ScholarData
    suspend fun save(scholarData: ScholarData)
}