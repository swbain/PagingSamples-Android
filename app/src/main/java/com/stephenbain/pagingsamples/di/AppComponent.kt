package com.stephenbain.pagingsamples.di

import com.stephenbain.pagingsamples.PagingSampleApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    LoginActivityModule::class,
    HomeActivityModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<PagingSampleApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PagingSampleApp>()
}