package com.example.cvscodingchallenge.data.model

import com.google.gson.annotations.SerializedName

data class FlickrResponse(
    @SerializedName("items") val items: List<FlickrImage>
)