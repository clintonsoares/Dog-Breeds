package com.csdev.designwaytest.ui.screens.login

import com.csdev.designwaytest.domain.model.User
import com.csdev.designwaytest.domain.useCase.users.LoginUserUseCase
import com.csdev.designwaytest.ui.BaseViewModel
import com.csdev.designwaytest.utils.Resource
import com.csdev.designwaytest.utils.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val userPreferences: UserPreferences
): BaseViewModel() {
    private val _isLoginSuccessful = MutableStateFlow(false)
    val isLoginSuccessful = _isLoginSuccessful.asStateFlow()
    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn = _isUserLoggedIn.asStateFlow()

    fun checkUserLoggedIn() {
        launch {
            startLoading()
            userPreferences.isLoggedIn.collect {
                _isUserLoggedIn.emit(it)
                stopLoading()
            }
        }
    }

    fun onLoginClicked(username: String, password: String) {
        val mUser = User(username = username,password = password)
        launch {
            startLoading("Logging in, please wait...")
            delay(2000)
            loginUserUseCase(mUser).collect { result ->
                stopLoading()
                when (result) {
                    is Resource.Success -> {
                        runBlocking {
                            userPreferences.setUserLoggedIn(true)
                        }
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