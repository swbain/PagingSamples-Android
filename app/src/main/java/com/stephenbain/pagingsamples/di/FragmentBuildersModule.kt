package com.stephenbain.pagingsamples.di

import com.stephenbain.pagingsamples.ui.home.HomeFragment
import com.stephenbain.pagingsamples.ui.newreleases.NewReleasesFragment
import com.stephenbain.pagingsamples.ui.playlists.MyPlaylistsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeNewReleasesFragment(): NewReleasesFragment

    @ContributesAndroidInjector
    abstract fun contributeMyPlaylistsFragment(): MyPlaylistsFragment
}