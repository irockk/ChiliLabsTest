package com.example.chililabstest.features.giphy.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.example.chililabstest.features.giphy.data.GiphyRepository
import com.example.chililabstest.features.giphy.data.models.toDomain
import com.example.chililabstest.features.giphy.domain.models.GifDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GiphyUseCase(private val giphyRepository: GiphyRepository) {

    fun getGifsPaged(): Flow<PagingData<GifDomainModel>> {
        return giphyRepository.getGifsPaged().map { flow -> flow.map { gif -> gif.toDomain() } }
    }
}