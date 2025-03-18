package com.example.chililabstest.features.giphy.data

import android.util.Log
import com.example.chililabstest.BuildConfig.GIPHY_API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class GiphyRepository(private val giphyService: GiphyService) {

    suspend fun getGifsPaged() {
        withContext(Dispatchers.IO) {
            val result = giphyService.getGifsPaged(GIPHY_API_KEY)
            if (result.isSuccessful) {
                Log.e("success", result.body().toString())
                Log.e("success", result.body()?.meta.toString())
            } else {
                Log.e("error", result.errorBody().toString())
                Log.e("error", result.body()?.meta.toString())
            }
        }
    }
}