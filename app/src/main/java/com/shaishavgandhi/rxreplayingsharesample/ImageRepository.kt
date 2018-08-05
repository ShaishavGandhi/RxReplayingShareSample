package com.shaishavgandhi.rxreplayingsharesample

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.RawRes
import io.reactivex.Observable

class ImageRepository(private val resources: Resources) {

    fun loadImage(@RawRes imageId: Int): Observable<Bitmap> {
        return Observable.fromCallable {
            return@fromCallable BitmapFactory.decodeStream(resources.openRawResource(imageId))
        }
    }
}