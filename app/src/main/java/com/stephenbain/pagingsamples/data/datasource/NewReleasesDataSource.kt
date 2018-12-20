package com.stephenbain.pagingsamples.data.datasource

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.stephenbain.pagingsamples.data.domain.GetNewReleases
import com.stephenbain.pagingsamples.data.model.AlbumSimple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class NewReleasesDataSource(
    private val scope: CoroutineScope,
    private val getNewReleases: GetNewReleases
) : PositionalDataSource<AlbumSimple>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<AlbumSimple>) {
        scope.launch {
            val paging = getNewReleases(
                offset = params.startPosition,
                limit = params.loadSize
            )
            callback.onResult(paging.items)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<AlbumSimple>) {
        scope.launch {
            val paging = getNewReleases(
                offset = params.requestedStartPosition,
                limit = params.pageSize
            )
            callback.onResult(paging.items, params.requestedStartPosition, paging.total)
        }
    }
}

class NewReleasesDataSourceFactory(
    private val scope: CoroutineScope,
    private val getNewReleases: GetNewReleases
) : DataSource.Factory<Int, AlbumSimple>() {
    override fun create(): DataSource<Int, AlbumSimple> {
        return NewReleasesDataSource(scope, getNewReleases)
    }
}