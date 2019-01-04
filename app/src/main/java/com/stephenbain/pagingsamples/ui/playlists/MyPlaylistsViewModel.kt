package com.stephenbain.pagingsamples.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stephenbain.pagingsamples.data.datasource.NewReleasesDataSourceFactory
import com.stephenbain.pagingsamples.data.datasource.PlaylistsBoundaryCallback
import com.stephenbain.pagingsamples.data.db.PlaylistDao
import com.stephenbain.pagingsamples.data.domain.GetMyPlaylists
import com.stephenbain.pagingsamples.data.model.Playlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject


class MyPlaylistsViewModel @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val getMyPlaylists: GetMyPlaylists
) : ViewModel() {

    private val parentJob = Job()
    private val scope = CoroutineScope(Dispatchers.Main + parentJob)

    val playlists: LiveData<PagedList<Playlist>>
        get() {
            val config = PagedList.Config.Builder().apply {
                setPageSize(20)
                setPrefetchDistance(60)
                setEnablePlaceholders(true)
            }.build()

            val factory = playlistDao.getAllPlaylists()
            return LivePagedListBuilder(factory, config)
                .setBoundaryCallback(PlaylistsBoundaryCallback(20, scope, playlistDao, getMyPlaylists))
                .build()
        }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}