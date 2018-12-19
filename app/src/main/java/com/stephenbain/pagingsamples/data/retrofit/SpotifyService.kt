package com.stephenbain.pagingsamples.data.retrofit

import com.stephenbain.pagingsamples.data.model.AlbumsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface SpotifyService {

    @GET("browse/new-releases")
    fun getNewReleases(
        @Header("Authorization") token: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): Deferred<AlbumsResponse>

}