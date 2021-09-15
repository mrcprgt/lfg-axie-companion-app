package com.lfgcompany.lfgaxiecompanionapp.app.presentation.stats

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.ScholarData
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.User
import com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.GetUserUseCase
import com.lfgcompany.lfgaxiecompanionapp.app.domain.usecase.scholardata.FetchScholarDataUseCase
import com.lfgcompany.lfgaxiecompanionapp.tools.CoroutineScopeProvider
import com.lfgcompany.lfgaxiecompanionapp.tools.LFGException
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.daysDifference
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.math.absoluteValue

class StatsPresenter @Inject constructor(
    private val scopeProvider: CoroutineScopeProvider,
    private val getUserUseCase: GetUserUseCase,
    private val fetchScholarDataUseCase: FetchScholarDataUseCase
) : StatsContract.Presenter {

    private var view: StatsContract.View? = null
    private var user: User? = null
    private var scholarData: ScholarData? = null

    override fun onViewReady(view: StatsContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onViewCreated() {
        scopeProvider.provide().launch {
            view?.showProgressDialog()
            fetchScholarData()
            showData()
            view?.hideProgressDialog()
        }

    }

    override fun onRefreshClicked() {
        scopeProvider.provide().launch {
            view?.showProgressDialog()
            fetchScholarData()
            showData()
            view?.hideProgressDialog()
        }
    }

    private suspend fun fetchScholarData() {
        return suspendCancellableCoroutine {
            scopeProvider.provide().launch {
                try {
                    user = getUserUseCase.execute(Unit).user
                    scholarData = fetchScholarDataUseCase.execute(Unit).scholarData
                    it.resume(Unit)
                } catch (e: LFGException) {
                    view?.showErrorDialog(
                        "Something went wrong",
                        e.message ?: e.localizedMessage,
                        onOkClicked = {
                            scopeProvider.provide().launch {
                                fetchScholarData()
                            }
                        },
                        onDeclineClicked = {
                            view?.hideProgressDialog()
                        })
                }
            }
        }
    }

    private suspend fun showData() {
        return suspendCancellableCoroutine {
            scopeProvider.provide().launch {
                try {

                    val daysDiff = daysDifference(scholarData?.lastClaimTimeStamp!!, Date())

                    val dailyAverage = scholarData?.inGameSlp!! / daysDiff

                    view?.showSlpCard(
                        dailyAverage = dailyAverage,
                        totalSlp = scholarData?.inGameSlp ?: 0,
                        managerShare = user?.managerShare!!,
                        scholarShare = user?.scholarShare!!
                    )

                    view?.showArenaCard(
                        mmr = scholarData?.mmr ?: 0,
                        wins = scholarData?.wins ?: 0,
                        draw = scholarData?.draws ?: 0,
                        lose = scholarData?.loses ?: 0,
                        winRate = scholarData?.winRate ?: 0.0,
                        arenaRank = scholarData?.arenaRank ?: 0
                    )

                    val lastClaim = scholarData?.lastClaimTimeStamp!!
                    val calendar = Calendar.getInstance()
                    calendar.time = lastClaim
                    calendar.add(Calendar.DATE, 14)
                    val nextClaimDate = calendar.time

                    view?.showClaimsCard(
                        lastClaimedAmount = scholarData?.lastClaimAmount ?: 0,
                        lastClaimedAt = scholarData?.lastClaimTimeStamp!!,
                        nextClaimDate = nextClaimDate,
                        nextClaimIn = daysDifference(nextClaimDate, Date()).absoluteValue
                    )

                    it.resume(Unit)
                } catch (e: LFGException) {
                    view?.hideProgressDialog()
                    view?.showErrorDialog(
                        "Something went wrong",
                        e.message ?: "Failed",
                        onOkClicked = {
                            scopeProvider.provide().launch {
                                showData()
                            }
                        },
                        onDeclineClicked = {
                            view?.hideProgressDialog()
                        })
                }
            }
        }
    }


}