package com.example.chililabstest.features.giphy.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.chililabstest.features.giphy.data.models.toDomain
import com.example.chililabstest.features.giphy.domain.models.GifDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GiphyRepository(private val giphyService: GiphyService) {
    fun getGifsPaged(): Flow<PagingData<GifDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ), pagingSourceFactory = { GiphyPagingSource(giphyService) }
        ).flow.map { page -> page.map { gif -> gif.toDomain() } }
    }

    fun getSearchedGifs(searchText: String): Flow<PagingData<GifDomainModel>> {
        return Pager(config = PagingConfig(
            pageSize = 25, enablePlaceholders = false
        ),
            pagingSourceFactory = {
                GiphyPagingSource(
                    giphyService,
                    searchText
                )
            }).flow.map { page -> page.map { gif -> gif.toDomain() } }
    }
}