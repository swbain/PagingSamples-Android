package com.stephenbain.pagingsamples.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stephenbain.pagingsamples.ui.home.HomeViewModel
import com.stephenbain.pagingsamples.ui.login.LoginViewModel
import com.stephenbain.spotifytools.android.viewmodel.PagingSampleViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMainViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: PagingSampleViewModelFactory): ViewModelProvider.Factory

}