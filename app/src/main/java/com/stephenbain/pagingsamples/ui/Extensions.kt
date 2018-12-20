package com.stephenbain.pagingsamples.ui

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar


inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    observe(owner, Observer { it?.let(observer) })
}

inline fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    observeNotNull(owner) { it.getContentIfNotHandled { value -> observer(value) } }
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory).get()
}

inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory).get()
}

fun snackbar(view: View, @StringRes text: Int, duration: Int = Snackbar.LENGTH_SHORT): Snackbar {
    return Snackbar.make(view, text, duration).apply { show() }
}

fun snackbar(view: View, text: String, duration: Int = Snackbar.LENGTH_SHORT): Snackbar {
    return Snackbar.make(view, text, duration).apply { show() }
}