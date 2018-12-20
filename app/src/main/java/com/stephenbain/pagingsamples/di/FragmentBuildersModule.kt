package com.stephenbain.pagingsamples.di

import com.stephenbain.pagingsamples.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}