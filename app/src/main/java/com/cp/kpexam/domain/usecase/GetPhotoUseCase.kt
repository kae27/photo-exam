package com.cp.kpexam.domain.usecase

import com.cp.kpexam.common.Either
import com.cp.kpexam.common.Failure
import com.cp.kpexam.common.UseCase
import com.cp.kpexam.common.map
import com.cp.kpexam.domain.model.Photo
import com.cp.kpexam.domain.repository.PhotoRepository

class GetPhotoUseCase(
    private val photoRepository: PhotoRepository
) : UseCase<MutableList<Photo>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, MutableList<Photo>> {
        val listPhoto: MutableList<Photo> = mutableListOf()
        return photoRepository.listPhoto().map {
            // map response for view model
            for (photo in it){
                listPhoto.add(
                    Photo(
                        photo.albumId ?: -1,
                        photo.id ?: -1,
                        photo.title ?: "",
                        photo.url ?: (photo.thumbnailUrl ?: "")
                    )
                )
            }
            return@map listPhoto
        }
    }
}