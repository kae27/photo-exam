package com.cp.kpexam.di

import com.cp.kpexam.domain.repository.PhotoRepository
import com.cp.kpexam.domain.usecase.GetPhotoUseCase
import com.cp.kpexam.features.photo.PhotoViewModel
import com.cp.kpexam.networking.PhotoRepositoryImpl
import com.cp.kpexam.networking.ViperaNetwork
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //UseCase
    single { GetPhotoUseCase(get()) }

    /* Network */
    single { ViperaNetwork.createNetworkClient(  "https://jsonplaceholder.typicode.com") }

    /* Repository */
    single<PhotoRepository> {
        PhotoRepositoryImpl(
            get()
        )
    }

    /* ViewModel */
    viewModel { PhotoViewModel(get()) }
}