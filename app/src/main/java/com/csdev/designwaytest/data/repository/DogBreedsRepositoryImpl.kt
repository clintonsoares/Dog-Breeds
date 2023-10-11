package com.csdev.designwaytest.data.repository

import com.csdev.designwaytest.data.remote.DogBreedsApi
import com.csdev.designwaytest.domain.model.DogBreedListItem
import com.csdev.designwaytest.domain.repository.DogBreedsRepository
import com.csdev.designwaytest.mappers.dogBreeds.DogBreedsListMapper
import com.csdev.designwaytest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogBreedsRepositoryImpl@Inject constructor(
    private val api: DogBreedsApi
): DogBreedsRepository {

    override suspend fun getDogBreedsList(limit: Int,page: Int): Flow<Resource<List<DogBreedListItem>>> {
        return flow {
            try {
                api.getDogBreeds(limit = limit, page = page).let { dto ->
                    val result = DogBreedsListMapper.map(dto)
                    emit(result)
                }
            } catch (e: Exception) {
                emit(
                    Resource.Error(e.localizedMessage ?: "Something went wrong at server end")
                )
            }
        }
    }

}