package com.csdev.designwaytest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.csdev.designwaytest.data.local.dao.UserDAO
import com.csdev.designwaytest.data.local.entity.UserEntity

// user database
@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract val dao: UserDAO
}