package com.cp.kpexam.domain.repository

import com.cp.kpexam.common.Either
import com.cp.kpexam.common.Failure
import com.cp.kpexam.networking.model.PhotoResponseModel

interface PhotoRepository {
    suspend fun listPhoto(): Either<Failure, List<PhotoResponseModel>>
}