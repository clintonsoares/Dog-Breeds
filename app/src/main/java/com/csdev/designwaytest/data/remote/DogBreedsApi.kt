package com.csdev.designwaytest.data.remote

import com.csdev.designwaytest.data.remote.dto.DogBreedsListDtoItem
import com.csdev.designwaytest.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogBreedsApi {

    @GET(ApiConstants.DOG_BREEDS_LIST_URL)
    suspend fun getDogBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<DogBreedsListDtoItem>

    @GET(ApiConstants.DOG_BREED_DETAILS_URL+"{reference_image_id}")
    suspend fun getDogBreedDetails(
        @Path("reference_image_id") referenceImageId: String
    )

}