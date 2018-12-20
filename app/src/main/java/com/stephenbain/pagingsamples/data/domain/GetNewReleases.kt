package com.stephenbain.pagingsamples.data.domain

import com.stephenbain.pagingsamples.data.model.AlbumSimple
import com.stephenbain.pagingsamples.data.model.Paging
import com.stephenbain.pagingsamples.data.repo.TokenRepository
import com.stephenbain.pagingsamples.data.retrofit.SpotifyService
import javax.inject.Inject


class GetNewReleases @Inject constructor(
    private val spotifyService: SpotifyService,
    private val tokenRepository: TokenRepository
) {

    suspend operator fun invoke(offset: Int = 0, limit: Int = 20): Paging<AlbumSimple> {
        return spotifyService.getNewReleases(
            token = "Bearer ${tokenRepository.token}",
            offset = offset,
            limit = limit
        ).await().albums
    }

}