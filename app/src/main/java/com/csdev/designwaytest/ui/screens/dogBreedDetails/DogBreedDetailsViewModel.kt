package com.csdev.designwaytest.ui.screens.dogBreedDetails

import com.csdev.designwaytest.domain.model.DogBreedDetails
import com.csdev.designwaytest.domain.useCase.dogbreeds.GetDogBreedDetailsUseCase
import com.csdev.designwaytest.ui.BaseViewModel
import com.csdev.designwaytest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DogBreedDetailsViewModel @Inject constructor(
    private val dogBreedDetailsUseCase: GetDogBreedDetailsUseCase
): BaseViewModel() {
    private val _dogBreedDetails = MutableStateFlow<DogBreedDetails?>(null)
    val dogBreedDetails = _dogBreedDetails.asStateFlow()

    fun getDogBreedDetails(referenceId: String) {
        launch {
            startLoading("Fetching details, please wait...")
            dogBreedDetailsUseCase(referenceId).collect { result ->
                val data = result.data
                stopLoading()
                when (result) {
                    is Resource.Success -> {
                        data?.let { _dogBreedDetails.emit(it) }
                    }
                    is Resource.Error -> {
                        showError(result.message ?: "Error fetching data")
                    }
                }
            }
        }
    }

}