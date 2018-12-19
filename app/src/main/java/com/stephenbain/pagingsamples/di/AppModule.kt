package com.stephenbain.pagingsamples.di

import android.content.Context
import com.stephenbain.pagingsamples.PagingSampleApp
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, AppModule.BindsModule::class])
class AppModule {

    @Module(includes = [AndroidSupportInjectionModule::class])
    interface BindsModule {
        @Singleton
        @Binds
        fun provideContext(app: PagingSampleApp): Context
    }

}