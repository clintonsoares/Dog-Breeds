package com.csdev.designwaytest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csdev.designwaytest.utils.set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loadingMessage = MutableStateFlow<String?>(null)
    val loadingMessage = _loadingMessage.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    protected fun startLoading(message: String? = null) {
        message?.let {
            viewModelScope.launch(Dispatchers.Default) {
                _loadingMessage.emit(it)
            }
        }
        _isLoading.postValue(true)
    }

    protected fun setLoadingMessage(message: String) {
        viewModelScope.launch(Dispatchers.Default) {
            _loadingMessage.emit(message)
        }
    }

    protected fun stopLoading() {
        _isLoading.postValue(false)
    }

    protected fun showError(errorMessage: String) {
        viewModelScope.launch(Dispatchers.Default) {
            _errorMessage.set(errorMessage)
        }
    }

    protected fun launch(
        loaderEnabled: Boolean = true,
        context: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context) {
            if (loaderEnabled) {
                _isLoading.postValue(true)
                block()
                _isLoading.postValue(false)
            } else {
                block()
            }
        }
    }
}