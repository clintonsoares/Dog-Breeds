package com.csdev.designwaytest.ui.screens.dogBreedList

import com.csdev.designwaytest.domain.model.DogBreedListItem
import com.csdev.designwaytest.domain.useCase.dogbreeds.GetDogBreedsUseCase
import com.csdev.designwaytest.ui.BaseViewModel
import com.csdev.designwaytest.utils.Resource
import com.csdev.designwaytest.utils.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DogBreedListViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetDogBreedsUseCase,
    private val userPreferences: UserPreferences
): BaseViewModel() {
    private val _dogBreeds = MutableStateFlow(listOf<DogBreedListItem>())
    val dogBreeds = _dogBreeds.asStateFlow()
    private val _isLogoutSuccessful = MutableStateFlow(false)
    val isLogoutSuccessful = _isLogoutSuccessful.asStateFlow()

    fun getDogBreeds() {
        launch {
            startLoading("Fetching dog breeds...")
            getDogBreedsUseCase(
                limit = 20,
                page = 0
            ).collect { result ->
                val mList = result.data
                stopLoading()
                when (result) {
                    is Resource.Success -> {
                        mList?.let { _dogBreeds.emit(it) }
                    }
                    is Resource.Error -> {
                        showError(result.message ?: "Error fetching data")
                    }
                }

            }
        }
    }

    fun userLogOut() {
        launch {
            startLoading()
            userPreferences.setUserLoggedIn(false).collect {
                _isLogoutSuccessful.emit(it)
            }
        }
    }

}