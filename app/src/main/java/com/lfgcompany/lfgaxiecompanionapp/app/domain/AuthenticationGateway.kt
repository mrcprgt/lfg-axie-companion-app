package com.lfgcompany.lfgaxiecompanionapp.app.domain

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.User

interface AuthenticationGateway {
    suspend fun login(user: User)
    suspend fun getUser(): User
}