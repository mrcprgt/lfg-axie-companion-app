package com.lfgcompany.lfgaxiecompanionapp.app.data.user

import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.User

object UserMapper {
    fun User.toLocalUser(): LocalUser = LocalUser(
        ronin = this.ronin,
        managerShare = this.managerShare,
        scholarShare = this.scholarShare,
        initialSlp = this.initialSlp
    )

    fun LocalUser.toDomain() : User = User(
        ronin = this.ronin,
        managerShare = this.managerShare,
        scholarShare = this.scholarShare,
        initialSlp = this.initialSlp
    )
}