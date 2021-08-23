package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.scholardata

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.ScholarDataGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.ScholarData
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.Interactor
import com.mrcprgt.lfgaxiecompanionapp.tools.interactor.InteractorHandler
import javax.inject.Inject

class FetchScholarDataUseCase @Inject constructor(
    interactorHandler: InteractorHandler,
    private val scholarDataGateway: ScholarDataGateway,
    private val authenticationGateway: AuthenticationGateway
) : Interactor<FetchScholarDataUseCase.Response, Unit>(interactorHandler) {
    override suspend fun run(params: Unit): Response {
        val ronin = authenticationGateway.getUser().ronin
        val scholarData = scholarDataGateway.fetchScholarData(ronin)
        scholarDataGateway.save(scholarData)
        return Response(scholarData)
    }

    data class Response(
        val scholarData: ScholarData
    )

}