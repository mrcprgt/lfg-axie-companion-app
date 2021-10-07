package com.lfgcompany.lfgaxiecompanionapp.app.data.user

import com.lfgcompany.lfgaxiecompanionapp.app.data.user.UserMapper.toDomain
import com.lfgcompany.lfgaxiecompanionapp.app.data.user.UserMapper.toLocalUser
import com.lfgcompany.lfgaxiecompanionapp.app.domain.AuthenticationGateway
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.User
import com.lfgcompany.lfgaxiecompanionapp.tools.scopes.NoSessionException
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(private val userDao: UserDao) :
    AuthenticationGateway {
    override suspend fun login(user: User) {
        userDao.save(user.toLocalUser())
    }

    override suspend fun getUser(): User {
        return userDao.get()?.toDomain() ?: throw NoSessionException("No session.")
    }
}