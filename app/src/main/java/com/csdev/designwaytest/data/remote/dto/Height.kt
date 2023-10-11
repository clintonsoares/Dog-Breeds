package com.csdev.designwaytest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Height(

	@field:SerializedName("metric")
	val metric: String? = null,

	@field:SerializedName("imperial")
	val imperial: String? = null
)