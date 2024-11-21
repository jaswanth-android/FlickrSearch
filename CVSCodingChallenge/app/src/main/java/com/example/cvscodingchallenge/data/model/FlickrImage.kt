package com.example.cvscodingchallenge.data.model

import com.google.gson.annotations.SerializedName


data class FlickrImage(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("author") val author: String,
    @SerializedName("published") val published: String,
    @SerializedName("media") val media: Media
)
