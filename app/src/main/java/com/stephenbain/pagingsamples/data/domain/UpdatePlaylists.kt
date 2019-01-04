package com.stephenbain.pagingsamples.data.domain

import com.stephenbain.pagingsamples.data.db.PlaylistDao
import com.stephenbain.pagingsamples.data.repo.TokenRepository
import com.stephenbain.pagingsamples.data.retrofit.SpotifyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UpdatePlaylists @Inject constructor(
    private val spotifyService: SpotifyService,
    private val playlistDao: PlaylistDao,
    private val tokenRepository: TokenRepository
) {

    suspend operator fun invoke(limit: Int) {
        withContext(Dispatchers.IO) {
            val paging = spotifyService.getMyPlaylists(
                token = "Bearer ${tokenRepository.token}",
                offset = playlistDao.getItemCount(),
                limit = limit
            ).await()
            playlistDao.insertAll(paging.items)
        }
    }

}