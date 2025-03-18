package com.example.chililabstest.features.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chililabstest.features.giphy.data.GiphyRepository
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class CatalogViewModel(private val giphyRepository: GiphyRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            giphyRepository.getGifsPaged()
        }
    }
}