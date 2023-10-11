package com.csdev.designwaytest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Entity for User table
@Entity
data class UserEntity(
    val fullName: String,
    var userName: String,
    var password: String
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int=0

}