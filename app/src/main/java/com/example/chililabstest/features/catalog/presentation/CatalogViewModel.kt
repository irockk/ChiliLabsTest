package com.example.chililabstest.features.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.chililabstest.features.giphy.domain.GiphyUseCase
import com.example.chililabstest.features.giphy.presentation.GifPresentationModel
import com.example.chililabstest.features.giphy.presentation.toPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

data class CatalogState(
    val gifs: Flow<PagingData<GifPresentationModel>> = emptyFlow()
)
@Factory
class CatalogViewModel(private val giphyUseCase: GiphyUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogState())
    val uiState = _uiState.asStateFlow()

    init {
        setGifs()
    }

    private fun setGifs() {
        viewModelScope.launch {
            _uiState.update { uiState ->
                uiState.copy(
                    gifs = giphyUseCase.getGifsPaged().map { flow ->
                        flow.map { gif -> gif.toPresentation() }
                    })
            }
        }
    }
}