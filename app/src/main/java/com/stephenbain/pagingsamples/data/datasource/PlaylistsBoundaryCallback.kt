package com.stephenbain.pagingsamples.data.datasource

import com.stephenbain.pagingsamples.data.db.PlaylistDao
import com.stephenbain.pagingsamples.data.domain.GetMyPlaylists
import com.stephenbain.pagingsamples.data.model.Playlist
import kotlinx.coroutines.CoroutineScope


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
        val paging = getMyPlaylists(limit = pageSize, offset = offset)
        playlistDao.insertAll(paging.items)
    }

}