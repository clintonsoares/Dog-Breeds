package com.csdev.designwaytest.mappers.dogBreeds

import com.csdev.designwaytest.data.remote.dto.DogBreedsListDtoItem
import com.csdev.designwaytest.domain.model.DogBreedListItem
import com.csdev.designwaytest.mappers.BaseMapper
import com.csdev.designwaytest.utils.Resource

object DogBreedsListMapper : BaseMapper<List<DogBreedsListDtoItem>,Resource<List<DogBreedListItem>>>() {

    override fun map(input: List<DogBreedsListDtoItem>): Resource<List<DogBreedListItem>> {
        return try {
            val dogBreeds = mutableListOf<DogBreedListItem>()
            // map input entity to breeds list and send it in Resource
            // check if dog breeds from the response are not empty
            if (input.isNotEmpty()) {
                input.forEach { inputItem ->
                    val dogBreedListItem = DogBreedListItem(
                        name = inputItem.name ?: "",
                        reference_image_id = inputItem.referenceImageId ?: ""
                    )
                    dogBreeds.add(dogBreedListItem)
                }
            }
            Resource.Success(dogBreeds)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Something went wrong at server end")
        }
    }

}