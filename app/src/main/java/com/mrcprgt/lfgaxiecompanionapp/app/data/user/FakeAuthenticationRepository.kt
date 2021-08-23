package com.mrcprgt.lfgaxiecompanionapp.app.data.user

import com.mrcprgt.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeAuthenticationRepository @Inject constructor() : AuthenticationGateway{
    override suspend fun login(user: User) {
        delay(5000)
    }

    override suspend fun getUser(): User {
        return User(
            "0xasdadadasdasdada",
            50,
            50,
            1
        )
    }
}