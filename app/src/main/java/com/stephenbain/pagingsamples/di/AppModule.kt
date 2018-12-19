package com.stephenbain.pagingsamples.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.stephenbain.pagingsamples.PagingSampleApp
import com.stephenbain.pagingsamples.data.retrofit.SpotifyService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, AppModule.BindsModule::class])
class AppModule {

    @Module(includes = [AndroidSupportInjectionModule::class])
    interface BindsModule {
        @Singleton
        @Binds
        fun provideContext(app: PagingSampleApp): Context
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl("https://api.spotify.com/v1/")
            addConverterFactory(MoshiConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }.build()
    }

    @Provides
    @Singleton
    fun providesSpotifyService(retrofit: Retrofit): SpotifyService {
        return retrofit.create(SpotifyService::class.java)
    }

}