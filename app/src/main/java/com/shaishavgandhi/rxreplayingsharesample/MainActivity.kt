package com.shaishavgandhi.rxreplayingsharesample

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    // Lifecycle scope using Uber's AutoDispose for automatic disposing of Observables.
    private val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    // Custom view model factory
    private val viewModelFactory by lazy { MainViewModel.Factory(ImageRepository(resources)) }

    // ViewModel for given Activity
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button.setOnClickListener {
            loadImage()
        }
    }

    private fun loadImage() {
        viewModel.loadBitmap(R.raw.sunset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(scopeProvider)
            .subscribe({ bitmap ->
               imageView.setImageBitmap(bitmap)
            }, { error ->

            })
    }
}
