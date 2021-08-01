package com.mrcprgt.lfgaxiecompanionapp.app.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrcprgt.lfgaxiecompanionapp.app.data.user.LocalUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: LocalUser)

    @Query("SELECT * FROM user LIMIT 1")
    fun get(): LocalUser?

    @Query("DELETE FROM user")
    fun clear()
}
