package com.shaishavgandhi.rxreplayingsharesample

import android.content.res.Resources
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observable

class MainViewModel(private val repository: ImageRepository): ViewModel() {


    fun loadBitmap(imageId: Int): Observable<Bitmap> {
        return repository.loadImage(imageId)
    }

    class Factory(private val imageRepository: ImageRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(imageRepository) as T
        }
    }
}