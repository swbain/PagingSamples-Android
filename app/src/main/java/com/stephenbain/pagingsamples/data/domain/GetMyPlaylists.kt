package com.stephenbain.pagingsamples.data.domain

import com.stephenbain.pagingsamples.data.model.Paging
import com.stephenbain.pagingsamples.data.model.Playlist
import com.stephenbain.pagingsamples.data.repo.TokenRepository
import com.stephenbain.pagingsamples.data.retrofit.SpotifyService
import javax.inject.Inject


class GetMyPlaylists @Inject constructor(
    private val spotifyService: SpotifyService,
    private val tokenRepository: TokenRepository
) {

    suspend operator fun invoke(offset: Int = 0, limit: Int = 20): Paging<Playlist> {
        return spotifyService.getMyPlaylists(
            token = "Bearer ${tokenRepository.token}",
            offset = offset,
            limit = limit
        ).await()
    }

}