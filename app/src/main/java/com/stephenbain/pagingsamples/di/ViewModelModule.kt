package com.stephenbain.pagingsamples.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stephenbain.pagingsamples.ui.MainViewModel
import com.stephenbain.spotifytools.android.viewmodel.PagingSampleViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: PagingSampleViewModelFactory): ViewModelProvider.Factory

}