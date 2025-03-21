package com.example.chililabstest.features.giphy.domain

import androidx.paging.PagingData
import com.example.chililabstest.features.giphy.data.GiphyRepository
import com.example.chililabstest.features.giphy.domain.models.GifDomainModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GiphyUseCase(private val giphyRepository: GiphyRepository) {

    fun getGifsPaged(): Flow<PagingData<GifDomainModel>> = giphyRepository.getGifsPaged()

    fun getSearchedGifsPaged(searchText: String): Flow<PagingData<GifDomainModel>> {
        return giphyRepository.getSearchedGifs(searchText)
    }
}