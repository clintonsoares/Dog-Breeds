package com.csdev.designwaytest.mappers.dogBreeds

import com.csdev.designwaytest.data.remote.dto.DogBreedDetailsDto
import com.csdev.designwaytest.domain.model.DogBreedDetails
import com.csdev.designwaytest.domain.model.DogBreedListItem
import com.csdev.designwaytest.domain.model.Height
import com.csdev.designwaytest.domain.model.Weight
import com.csdev.designwaytest.mappers.BaseMapper
import com.csdev.designwaytest.utils.Resource

object DogBreedDetailsMapper : BaseMapper<DogBreedDetailsDto, Resource<DogBreedDetails>>() {

    override fun map(input: DogBreedDetailsDto): Resource<DogBreedDetails> {
        return try {
            // map input entity to breed details and send it in Resource
            val breedDetails = DogBreedDetails().apply {
                imageUrl = input.url
            }

            input.breeds?.forEach { itemNullable ->
                itemNullable?.let { item ->
                    breedDetails.apply {
                        name = item.name
                        bredFor = item.bredFor
                        breedGroup = item.breedGroup
                        lifeSpan = item.lifeSpan
                        temperament = item.temperament
                        origin = item.origin
                        referenceImageId = item.referenceImageId
                        item.weight?.let {
                            weight = Weight(
                                imperial = it.imperial,
                                metric = it.metric
                            )
                            height = Height(
                                imperial = it.imperial,
                                metric = it.metric
                            )
                        }
                    }
                }
            }

            Resource.Success(breedDetails)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Something went wrong at server end")
        }
    }

}