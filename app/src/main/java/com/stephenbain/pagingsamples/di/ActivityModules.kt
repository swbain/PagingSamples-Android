package com.stephenbain.pagingsamples.di

import com.stephenbain.pagingsamples.ui.MainActivity
import com.stephenbain.pagingsamples.ui.newreleases.NewReleasesActivity
import com.stephenbain.pagingsamples.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class LoginActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
}

@Module
abstract class NewReleasesModule {
    @ContributesAndroidInjector
    abstract fun contributeNewReleasesActivity(): NewReleasesActivity
}

@Module(includes = [FragmentBuildersModule::class])
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}