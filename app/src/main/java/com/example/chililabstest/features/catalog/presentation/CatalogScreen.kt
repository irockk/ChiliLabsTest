package com.example.chililabstest.features.catalog.presentation

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.SubcomposeAsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import com.example.chililabstest.features.giphy.data.models.GifRemoteModel
import com.example.chililabstest.ui.components.ErrorPlaceholder
import com.example.chililabstest.ui.components.LoadingShimmer
import com.example.chililabstest.ui.theme.ChiliLabsTestTheme
import com.example.chililabstest.ui.theme.Dimens
import com.example.chililabstest.ui.theme.Dimens.gifMinSize

@Composable
fun CatalogScreen(
    uiState: CatalogState
) {
    Gifs(
        modifier = Modifier.clip(RoundedCornerShape(Dimens.cornerRadius)),
        gifs = uiState.gifs
    )
}

@Composable
fun Gifs(
    modifier: Modifier = Modifier, gifs: List<GifRemoteModel>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(gifMinSize),
    ) {
        items(items = gifs, key = { gif -> gif.id }) { gif ->
            SubcomposeAsyncImage(modifier = modifier,
                model = ImageRequest.Builder(LocalContext.current).data(gif.images.original.url)
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
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ChiliLabsTestTheme {
        CatalogScreen(
            uiState = CatalogState()
        )
    }
}