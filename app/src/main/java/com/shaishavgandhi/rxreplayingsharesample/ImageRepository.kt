package com.shaishavgandhi.rxreplayingsharesample

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.RawRes
import com.jakewharton.rx.replayingShare
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class ImageRepository(private val resources: Resources) {

    companion object {
        private const val TAG = "ImageRepository"
    }

    private val subject = PublishSubject.create<Int>()

    private val imageObservable = subject
        .map {
            Log.d(TAG, "Performing expensive operation")
            return@map BitmapFactory.decodeStream(resources.openRawResource(it))
        }.replayingShare()

    fun image(): Observable<Bitmap> {
        return imageObservable
    }

    fun loadImage(@RawRes imageId: Int) {
        subject.onNext(imageId)
    }
}
