package com.csdev.designwaytest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DogBreedDetailsDto(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("breeds")
	val breeds: List<BreedsItem?>? = null,

	@field:SerializedName("height")
	val height: Int? = null
)