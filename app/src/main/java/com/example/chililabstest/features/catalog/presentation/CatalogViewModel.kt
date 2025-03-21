package com.example.chililabstest.features.catalog.presentation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.chililabstest.core.Constants.DEBOUNCE_TIME
import com.example.chililabstest.features.giphy.domain.GiphyUseCase
import com.example.chililabstest.features.giphy.presentation.GifPresentationModel
import com.example.chililabstest.features.giphy.presentation.toPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

data class CatalogState(
    val gifs: Flow<PagingData<GifPresentationModel>> = emptyFlow(),
    val searchedGifs: Flow<PagingData<GifPresentationModel>> = emptyFlow(),
    val searchText: TextFieldValue = TextFieldValue()
)
@Factory
class CatalogViewModel(private val giphyUseCase: GiphyUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        setGifs()
        performSearch()
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

    private fun performSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState
                .map { it.searchText.text }
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(DEBOUNCE_TIME)
                .collectLatest { searchText ->
                    searchJob?.cancel()
                    searchJob = viewModelScope.launch {
                        _uiState.update { uiState ->
                            uiState.copy(
                                gifs = giphyUseCase.getSearchedGifsPaged(searchText).map { flow ->
                                    flow.map { gif -> gif.toPresentation() }
                                })
                        }
                    }
                }
        }
    }

    fun setSearchText(text: TextFieldValue) {
        _uiState.update { uiState -> uiState.copy(searchText = text) }
    }
}