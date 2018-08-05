package com.shaishavgandhi.rxreplayingsharesample

import android.content.res.Resources
import android.graphics.Bitmap
import androidx.annotation.RawRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observable

class MainViewModel(private val repository: ImageRepository): ViewModel() {

    fun image(): Observable<Bitmap> {
        return repository.image()
    }

    fun loadImage(@RawRes imageId: Int) {
        repository.loadImage(imageId)
    }

    class Factory(private val imageRepository: ImageRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(imageRepository) as T
        }
    }
}