package com.example.chililabstest.features.catalog.presentation

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.SubcomposeAsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import com.example.chililabstest.R
import com.example.chililabstest.features.giphy.presentation.GifPresentationModel
import com.example.chililabstest.ui.components.ErrorPlaceholder
import com.example.chililabstest.ui.components.LoadingShimmer
import com.example.chililabstest.ui.theme.ChiliLabsTestTheme
import com.example.chililabstest.ui.theme.Dimens
import com.example.chililabstest.ui.theme.Dimens.gifMinSize

@Composable
fun CatalogScreen(
    uiState: CatalogState,
    updateSearchText: (newText: TextFieldValue) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.screenPadding),
            text = uiState.searchText,
            onTextChange = updateSearchText
        )

        val gifs = uiState.gifs.collectAsLazyPagingItems()
        val searchedGifs = uiState.searchedGifs.collectAsLazyPagingItems()

        val gifsToShow = remember(uiState.searchText.text.isBlank()) {
            if (uiState.searchText.text.isBlank()) gifs else searchedGifs
        }

        Gifs(
            modifier = Modifier.clip(RoundedCornerShape(Dimens.cornerRadius)),
            gifs = gifsToShow
        )
    }
}

@Composable
fun Gifs(
    modifier: Modifier = Modifier,
    gifs: LazyPagingItems<GifPresentationModel>
) {
    when (gifs.loadState.refresh) {
        is LoadState.Loading -> LoadingContent(modifier = Modifier.fillMaxSize())

        is LoadState.Error -> {
            ErrorContent(
                modifier = Modifier.fillMaxSize(),
                retry = { gifs.retry() }
            )
        }

        is LoadState.NotLoading -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(gifMinSize),
            ) {
                items(
                    count = gifs.itemCount,
                    key = { it }
                ) { index ->
                    SubcomposeAsyncImage(
                        modifier = modifier,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(gifs[index]?.url)
                            .decoderFactory(if (SDK_INT >= 28) AnimatedImageDecoder.Factory() else GifDecoder.Factory())
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        loading = {
                            LoadingShimmer(
                                modifier = Modifier
                                    .size(gifMinSize)
                                    .clip(RoundedCornerShape(Dimens.cornerRadius))
                            )
                        },
                        error = {
                            ErrorPlaceholder(modifier = Modifier.size(gifMinSize))
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchField(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    onTextChange: (newText: TextFieldValue) -> Unit
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChange,
        label = {
            Text(stringResource(R.string.search_label))
        },
        shape = RoundedCornerShape(Dimens.cornerRadius),
        leadingIcon = {
            Icon(
                painter = rememberVectorPainter(Icons.Filled.Search),
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
    )
}

@Composable
private fun ErrorContent(
    modifier: Modifier = Modifier,
    retry: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.error_text),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(Dimens.paddingSmall))

        Button(onClick = retry) {
            Text(stringResource(R.string.retry_button_text))
        }
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ChiliLabsTestTheme {
        CatalogScreen(
            uiState = CatalogState(),
            updateSearchText = {}
        )
    }
}