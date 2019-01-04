package com.stephenbain.pagingsamples.data.datasource

import com.stephenbain.pagingsamples.data.domain.UpdatePlaylists
import com.stephenbain.pagingsamples.data.model.Playlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import timber.log.Timber


class PlaylistsBoundaryCallback(
    scope: CoroutineScope,
    private val pageSize: Int,
    private val updatePlaylists: UpdatePlaylists
) : PositionalBoundaryCallback<Playlist>(pageSize, scope) {

    override suspend fun initialLoad() {
        Timber.d("initial load")
        updatePlaylists(limit = pageSize)
    }

    override suspend fun itemAtEndLoad(offset: Int) {
        Timber.d("loading item at end")
        try {
            updatePlaylists(limit = pageSize)
        } catch (t: Throwable) {
            Timber.e(t, "error getting playlists")
            // hack around rate limiting. delay by 200 and retry
            delay(200)
            itemAtEndLoad(offset)
        }
    }

}