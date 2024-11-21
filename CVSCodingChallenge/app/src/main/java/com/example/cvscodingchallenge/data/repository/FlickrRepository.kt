package com.example.cvscodingchallenge.data.repository


import com.example.cvscodingchallenge.data.model.FlickrImage
import com.example.cvscodingchallenge.data.network.FlickrApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlickrRepository @Inject constructor(
    private val apiService: FlickrApiService
) {
    suspend fun searchImages(tags: String): List<FlickrImage> = withContext(Dispatchers.IO) {
        try {
            apiService.searchPhotos(tags = tags).items
        } catch (e: Exception) {
            emptyList()
        }
    }
}