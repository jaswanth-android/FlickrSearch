package com.example.cvscodingchallenge.data.network

import com.example.cvscodingchallenge.data.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("services/feeds/photos_public.gne")
    suspend fun searchPhotos(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("tags") tags: String
    ): FlickrResponse
}