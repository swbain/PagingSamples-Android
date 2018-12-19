package com.stephenbain.pagingsamples.di

import com.spotify.sdk.android.authentication.LoginActivity
import com.stephenbain.pagingsamples.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class LoginActivityModule() {
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
}

@Module
abstract class MainActivityModule() {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}