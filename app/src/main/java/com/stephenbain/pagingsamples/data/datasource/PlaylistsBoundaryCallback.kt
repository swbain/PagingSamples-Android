package com.stephenbain.pagingsamples.data.datasource

import com.stephenbain.pagingsamples.data.db.PlaylistDao
import com.stephenbain.pagingsamples.data.domain.GetMyPlaylists
import com.stephenbain.pagingsamples.data.model.Playlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import timber.log.Timber


class PlaylistsBoundaryCallback(
    private val pageSize: Int,
    scope: CoroutineScope,
    private val playlistDao: PlaylistDao,
    private val getMyPlaylists: GetMyPlaylists
) : PositionalBoundaryCallback<Playlist>(pageSize, scope) {

    override suspend fun initialLoad() {
        val paging = getMyPlaylists(limit = pageSize)
        playlistDao.insertAll(paging.items)
    }

    override suspend fun itemAtEndLoad(offset: Int) {
        try {
            val paging = getMyPlaylists(limit = pageSize, offset = playlistDao.getItemCount())
            playlistDao.insertAll(paging.items)
        } catch (t: Throwable) {
            Timber.e(t, "error getting playlists")
            // hack around rate limiting. delay by 200 and retry
            delay(200)
            itemAtEndLoad(offset)
        }
    }

}