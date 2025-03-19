package com.example.chililabstest.features.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chililabstest.features.giphy.data.GiphyRepository
import com.example.chililabstest.features.giphy.data.models.GifRemoteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

data class CatalogState(
    val gifs: List<GifRemoteModel> = emptyList()
)
@Factory
class CatalogViewModel(private val giphyRepository: GiphyRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = giphyRepository.getGifsPaged()
            if (result.isSuccess) {
                _uiState.update { uiState -> uiState.copy(gifs = result.getOrThrow()) }
            }
        }
    }
}