package com.csdev.designwaytest.ui.screens.login

import com.csdev.designwaytest.domain.model.User
import com.csdev.designwaytest.domain.useCase.users.LoginUserUseCase
import com.csdev.designwaytest.ui.BaseViewModel
import com.csdev.designwaytest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
): BaseViewModel() {
    private val _isLoginSuccessful = MutableStateFlow(false)
    val isLoginSuccessful = _isLoginSuccessful.asStateFlow()

    fun onLoginClicked(username: String, password: String) {
        val mUser = User(username = username,password = password)
        launch {
            startLoading("Logging in, please wait...")
            delay(2000)
            loginUserUseCase(mUser).collect { result ->
                stopLoading()
                when (result) {
                    is Resource.Success -> {
                        _isLoginSuccessful.emit(true)
                    }
                    is Resource.Error -> {
                        showError(result.message ?: "Error logging in, please try again..")
                        _isLoginSuccessful.emit(false)
                    }
                }
            }
        }
    }

}