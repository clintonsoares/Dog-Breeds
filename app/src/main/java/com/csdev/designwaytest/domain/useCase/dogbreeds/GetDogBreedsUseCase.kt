package com.csdev.designwaytest.domain.useCase.dogbreeds

import com.csdev.designwaytest.domain.repository.DogBreedsRepository
import javax.inject.Inject

class GetDogBreedsUseCase @Inject constructor(
    private val dogBreedsRepository: DogBreedsRepository
) {
    suspend operator fun invoke(limit: Int,page: Int) = dogBreedsRepository.getDogBreedsList(limit, page)
}