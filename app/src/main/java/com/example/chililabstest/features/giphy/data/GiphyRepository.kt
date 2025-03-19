package com.example.chililabstest.features.giphy.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.chililabstest.features.giphy.data.models.GifRemoteModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GiphyRepository(private val giphyPagingSource: GiphyPagingSource) {

    fun getGifsPaged(): Flow<PagingData<GifRemoteModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { giphyPagingSource }
        ).flow
    }
}