package com.example.chililabstest.features.giphy.data.models

import com.example.chililabstest.features.giphy.domain.models.GifDomainModel

data class GifRemoteModel(
    val id: String,
    val images: Images
)

data class Images(
    val original: ImageDetails
)

data class ImageDetails(
    val url: String
)

fun GifRemoteModel.toDomain() = GifDomainModel(id = id, url = images.original.url)