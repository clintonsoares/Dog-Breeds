package com.csdev.designwaytest.domain.repository

import com.csdev.designwaytest.domain.model.User
import com.csdev.designwaytest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun registerUser(user: User): Flow<Resource<Boolean>>

    suspend fun loginUser(user: User): Flow<Resource<Boolean>>

}