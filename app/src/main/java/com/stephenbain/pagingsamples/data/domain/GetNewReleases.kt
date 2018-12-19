package com.stephenbain.pagingsamples.data.domain

import com.stephenbain.pagingsamples.data.model.AlbumSimple
import com.stephenbain.pagingsamples.data.model.Paging
import com.stephenbain.pagingsamples.data.retrofit.SpotifyService
import javax.inject.Inject


class GetNewReleases @Inject constructor(private val spotifyService: SpotifyService) {

    suspend operator fun invoke(token: String, offset: Int = 0): Paging<AlbumSimple> {
        return spotifyService.getNewReleases("Bearer $token", offset).await().albums
    }

}