package com.mrcprgt.lfgaxiecompanionapp.app.domain

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User

interface AuthenticationGateway {
    suspend fun login(user: User)
    suspend fun getUser(): User
}