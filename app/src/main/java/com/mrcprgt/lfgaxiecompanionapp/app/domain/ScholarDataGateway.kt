package com.mrcprgt.lfgaxiecompanionapp.app.domain

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData

interface ScholarDataGateway {
    suspend fun fetchScholarData(ronin: String) : ScholarData
    suspend fun get(): ScholarData
    suspend fun save(scholarData: ScholarData)
}