package com.csdev.designwaytest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.csdev.designwaytest.data.local.entity.UserEntity

@Dao
interface UserDAO {

    @Insert
    suspend fun addUser(userEntity: UserEntity)

    @Query("SELECT * FROM userEntity WHERE userName = :username")
    fun getUser(username: String) : UserEntity?

    @Query("SELECT EXISTS(SELECT * FROM userEntity WHERE userName = :username)")
    fun findUser(username: String) : Boolean

}