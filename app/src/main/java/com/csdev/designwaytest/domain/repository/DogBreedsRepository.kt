package com.csdev.designwaytest.domain.repository

import com.csdev.designwaytest.domain.model.DogBreedDetails
import com.csdev.designwaytest.domain.model.DogBreedListItem
import com.csdev.designwaytest.domain.model.User
import com.csdev.designwaytest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DogBreedsRepository {

    suspend fun getDogBreedsList(limit: Int,page: Int): Flow<Resource<List<DogBreedListItem>>>

    suspend fun getDogBreedDetails(referenceImageId: String): Flow<Resource<DogBreedDetails>>

}