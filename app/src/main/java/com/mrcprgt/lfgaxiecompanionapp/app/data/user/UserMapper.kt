package com.mrcprgt.lfgaxiecompanionapp.app.data.user

import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.User

object UserMapper {
    fun User.toLocalUser(): LocalUser = LocalUser(
        ronin = this.ronin,
        managerShare = this.managerShare,
        scholarShare = this.scholarShare
    )

    fun LocalUser.toDomain() : User = User(
        ronin = this.ronin,
        managerShare = this.managerShare,
        scholarShare = this.scholarShare
    )
}