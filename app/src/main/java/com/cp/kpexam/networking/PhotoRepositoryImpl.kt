package com.cp.kpexam.networking

import com.cp.kpexam.common.Either
import com.cp.kpexam.common.Failure
import com.cp.kpexam.domain.repository.PhotoRepository
import com.cp.kpexam.networking.api.ApiService
import com.cp.kpexam.networking.model.PhotoResponseModel

class PhotoRepositoryImpl(
    private val client: ApiService
): PhotoRepository {

    override suspend fun listPhoto(): Either<Failure, List<PhotoResponseModel>> {

        return try {
            val result = client.getPhotos()
            Either.Right(result)
        } catch (e: Exception) {
            Either.Left(Failure.Error(e.message ?: "", null, "-101")) // fixme: mock error
        }
    }
}