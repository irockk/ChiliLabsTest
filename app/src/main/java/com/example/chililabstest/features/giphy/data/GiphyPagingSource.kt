package com.example.chililabstest.features.giphy.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.chililabstest.BuildConfig
import com.example.chililabstest.features.giphy.data.models.GifRemoteModel

class GiphyPagingSource(
    private val giphyService: GiphyService,
    private val query: String? = null
) : PagingSource<Int, GifRemoteModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifRemoteModel> {
        val page = params.key ?: 0
        return try {
            val response = if (query.isNullOrEmpty()) {
                giphyService.getGifsPaged(
                    apiKey = BuildConfig.GIPHY_API_KEY,
                    limit = params.loadSize,
                    offset = page * params.loadSize
                )
            } else {
                giphyService.getSearchedGifsPaged(
                    apiKey = BuildConfig.GIPHY_API_KEY,
                    search = query,
                    limit = params.loadSize,
                    offset = page * params.loadSize
                )
            }

            val gifs = response.body()?.data ?: emptyList()

            LoadResult.Page(
                data = gifs,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (gifs.isEmpty()) null else page + 1
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifRemoteModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}