package com.example.chililabstest.features.giphy.data.models

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