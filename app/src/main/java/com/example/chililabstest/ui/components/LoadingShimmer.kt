package com.example.chililabstest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.chililabstest.ui.theme.ChiliLabsTestTheme
import com.example.chililabstest.ui.theme.Dimens.gifMinSize
import com.valentinilk.shimmer.shimmer

@Composable
fun LoadingShimmer(
    modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onPrimary
) {
    Box(
        modifier = modifier
            .background(Color.Gray)
            .shimmer()
    ) {
        Box(modifier = modifier.background(color))
    }
}

@Preview(showBackground = true)
@Composable
fun GifLoadingShimmerPreview() {
    ChiliLabsTestTheme {
        LoadingShimmer(
            modifier = Modifier.size(gifMinSize)
        )
    }
}