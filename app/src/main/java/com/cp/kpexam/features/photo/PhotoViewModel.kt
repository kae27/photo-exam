package com.cp.kpexam.features.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cp.kpexam.common.Failure
import com.cp.kpexam.common.UseCase
import com.cp.kpexam.domain.model.Photo
import com.cp.kpexam.domain.usecase.GetPhotoUseCase
import com.hadilq.liveevent.LiveEvent


class PhotoViewModel(
    private val getPhotoUseCase: GetPhotoUseCase
): ViewModel() {

    var failure: LiveEvent<Failure> = LiveEvent()
    val loadingProgressDialog = LiveEvent<Boolean>()
    val photosLiveData: LiveEvent<MutableList<Photo>> = LiveEvent()
    val popStack: Int = 0

    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    private fun handleLoadingProgressDialog(isLoading: Boolean) {
        loadingProgressDialog.value = isLoading
    }


    fun getListPhoto() {
        getPhotoUseCase(
            viewModelScope,
            UseCase.None(),
            ::handleLoadingProgressDialog) {
            it.either(
                ::handleFailure
            ) {p ->
                photosLiveData.postValue(p)
            }
        }
    }
}