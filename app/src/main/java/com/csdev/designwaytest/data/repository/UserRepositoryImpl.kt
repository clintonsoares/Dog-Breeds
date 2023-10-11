package com.csdev.designwaytest.data.repository

import com.csdev.designwaytest.data.local.dao.UserDAO
import com.csdev.designwaytest.data.local.entity.UserEntity
import com.csdev.designwaytest.domain.model.User
import com.csdev.designwaytest.domain.repository.UserRepository
import com.csdev.designwaytest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDAO
) : UserRepository {

    override suspend fun registerUser(user: User): Flow<Resource<Boolean>> {
        return dao.findUser(user.username).let { isRegistered ->
            flow {
                if (isRegistered) {
                    emit(Resource.Error("User is already registered. Please go to login."))
                } else {
                    val userEntity = user.toUserEntity
                    dao.addUser(userEntity)
                    emit(Resource.Success(true))
                }
            }
        }
    }

    override suspend fun loginUser(user: User): Flow<Resource<Boolean>> {
        val dBEntry = dao.getUser(user.username)

        dBEntry?.let { entry ->
            if (entry.password.equals(user.password,false)) {
                return flow {
                    emit(Resource.Success(true))
                }
            } else {
                return flow {
                    emit(Resource.Error("User password is incorrect."))
                }
            }
        } ?: run {
            return flow {
                emit(Resource.Error("User not found."))
            }
        }
    }

}