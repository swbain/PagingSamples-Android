package com.stephenbain.pagingsamples.data.datasource

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.stephenbain.pagingsamples.data.domain.GetNewReleases
import com.stephenbain.pagingsamples.data.model.AlbumSimple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class NewReleasesDataSource(
    private val scope: CoroutineScope,
    private val getNewReleases: GetNewReleases,
    private val token: String
) : PositionalDataSource<AlbumSimple>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<AlbumSimple>) {
        scope.launch {
            val paging = getNewReleases(token, params.startPosition)
            callback.onResult(paging.items)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<AlbumSimple>) {
        scope.launch {
            val paging = getNewReleases(token, params.requestedStartPosition)
            callback.onResult(paging.items, params.requestedStartPosition, paging.total)
        }
    }
}

class NewReleasesDataSourceFactory(
    private val scope: CoroutineScope,
    private val getNewReleases: GetNewReleases,
    private val token: String
) : DataSource.Factory<Int, AlbumSimple>() {
    override fun create(): DataSource<Int, AlbumSimple> {
        return NewReleasesDataSource(scope, getNewReleases, token)
    }
}