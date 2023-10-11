package com.csdev.designwaytest.ui.screens.dogBreedList

import com.csdev.designwaytest.domain.model.DogBreedListItem
import com.csdev.designwaytest.domain.useCase.dogbreeds.GetDogBreedsUseCase
import com.csdev.designwaytest.ui.BaseViewModel
import com.csdev.designwaytest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DogBreedListViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetDogBreedsUseCase
): BaseViewModel() {
    private val _dogBreeds = MutableStateFlow(listOf<DogBreedListItem>())
    val dogBreeds = _dogBreeds.asStateFlow()

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

}