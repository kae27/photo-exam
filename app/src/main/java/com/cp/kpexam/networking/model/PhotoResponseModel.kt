package com.cp.kpexam.networking.model

import com.google.gson.annotations.SerializedName

data class PhotoResponseModel(
    @SerializedName("albumId")
    var albumId: Int? = -1,
    @SerializedName("id")
    var id: Int? = -1,
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String? = ""
)