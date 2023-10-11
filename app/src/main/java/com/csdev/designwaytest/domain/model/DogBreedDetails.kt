package com.csdev.designwaytest.domain.model

// model class for single Dog Breed with extra details
data class DogBreedDetails(
    var name: String? = null,
    var bredFor: String? = null,
    var breedGroup: String? = null,
    var lifeSpan: String? = null,
    var temperament: String? = null,
    var origin: String? = null,
    var imageUrl: String? = null,
    var weight: Weight? = null,
    var height: Height? = null,
    var referenceImageId: String? = null
)

data class Weight(
    var imperial: String? = null,
    var metric: String? = null
)

data class Height(
    var imperial: String? = null,
    var metric: String? = null
)
