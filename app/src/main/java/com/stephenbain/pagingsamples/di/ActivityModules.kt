package com.stephenbain.pagingsamples.di

import com.stephenbain.pagingsamples.ui.home.HomeActivity
import com.stephenbain.pagingsamples.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class LoginActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
}

@Module
abstract class HomeActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): HomeActivity
}