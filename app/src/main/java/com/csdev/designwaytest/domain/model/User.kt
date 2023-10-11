package com.csdev.designwaytest.domain.model

import com.csdev.designwaytest.data.local.entity.UserEntity

// model class for User
data class User(
    var username: String = "",
    var password: String = "",
    var fullName: String = ""
) {
    val toUserEntity = UserEntity(
        fullName = fullName,
        userName = username,
        password = password
    )
}
