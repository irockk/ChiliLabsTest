package com.example.chililabstest.features.giphy.data.models

data class GifResponse(
    val data: List<GifRemoteModel>,
    val pagination: Pagination,
    val meta: Meta
)

data class Pagination(
    val totalCount: Int,
    val count: Int,
    val offset: Int
)

data class Meta(
    val status: Int,
    val msg: String
)