package com.csdev.designwaytest.domain.useCase.users

import com.csdev.designwaytest.domain.model.User
import com.csdev.designwaytest.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) = userRepository.registerUser(user)
}