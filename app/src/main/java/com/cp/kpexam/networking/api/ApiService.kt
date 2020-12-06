package com.cp.kpexam.networking.api

import com.cp.kpexam.networking.model.PhotoResponseModel
import retrofit2.http.*

interface ApiService {

    @GET("/albums/1/photos")
    suspend fun getPhotos(): List<PhotoResponseModel>


}