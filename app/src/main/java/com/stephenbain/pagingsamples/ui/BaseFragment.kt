package com.stephenbain.pagingsamples.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    protected inline fun <reified T : ViewModel> getViewModel(): T = getViewModel(viewModelFactory)

}