package com.stephenbain.pagingsamples.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stephenbain.pagingsamples.ui.home.HomeViewModel
import com.stephenbain.pagingsamples.ui.newreleases.NewReleasesViewModel
import com.stephenbain.pagingsamples.ui.login.LoginViewModel
import com.stephenbain.pagingsamples.ui.playlists.MyPlaylistsViewModel
import com.stephenbain.spotifytools.android.viewmodel.PagingSampleViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewReleasesViewModel::class)
    abstract fun bindNewReleasesViewModel(newReleasesViewModel: NewReleasesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyPlaylistsViewModel::class)
    abstract fun bindMyPlaylistsViewModel(myPlaylistsViewModel: MyPlaylistsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: PagingSampleViewModelFactory): ViewModelProvider.Factory

}