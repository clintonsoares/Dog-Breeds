package com.csdev.designwaytest.ui.screens.register

import com.csdev.designwaytest.domain.model.User
import com.csdev.designwaytest.domain.useCase.users.RegisterUserUseCase
import com.csdev.designwaytest.ui.BaseViewModel
import com.csdev.designwaytest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
): BaseViewModel() {
    private val _isRegistrationSuccessful = MutableStateFlow(false)
    val isRegistrationSuccessful = _isRegistrationSuccessful.asStateFlow()

    fun onRegisterClicked(username: String, password: String,fullName: String) {
        val mUser = User(username = username,password = password, fullName = fullName)
        launch {
            startLoading("Registering User, please wait...")
            launch {
                registerUserUseCase(mUser).collect { result ->
                    stopLoading()
                    when (result) {
                        is Resource.Success -> {
                            _isRegistrationSuccessful.emit(true)
                        }
                        is Resource.Error -> {
                            showError(result.message ?: "Error registering user, please try again..")
                            _isRegistrationSuccessful.emit(false)
                        }
                    }
                }
            }
        }
    }

}