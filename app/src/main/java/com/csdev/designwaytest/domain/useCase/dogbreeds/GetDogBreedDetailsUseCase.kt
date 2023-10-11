package com.csdev.designwaytest.domain.useCase.dogbreeds

import com.csdev.designwaytest.domain.repository.DogBreedsRepository
import javax.inject.Inject

class GetDogBreedDetailsUseCase@Inject constructor(
    private val dogBreedsRepository: DogBreedsRepository
) {
    suspend operator fun invoke(referenceImageId: String) = dogBreedsRepository.getDogBreedDetails(referenceImageId = referenceImageId)
}