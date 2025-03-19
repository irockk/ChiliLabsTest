package com.example.chililabstest.features.giphy.presentation

import com.example.chililabstest.features.giphy.domain.models.GifDomainModel

data class GifPresentationModel(
    val id: String,
    val url: String
)

fun GifDomainModel.toPresentation() = GifPresentationModel(id = id, url = url)