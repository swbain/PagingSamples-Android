package com.stephenbain.pagingsamples.data.datasource

import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


abstract class PositionalBoundaryCallback<T>(private val pageSize: Int, private val scope: CoroutineScope) : PagedList.BoundaryCallback<T>() {

    private var offset = 0

    abstract suspend fun initialLoad()

    abstract suspend fun itemAtEndLoad(offset: Int)

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        scope.launch { initialLoad() }
    }

    override fun onItemAtEndLoaded(itemAtEnd: T) {
        super.onItemAtEndLoaded(itemAtEnd)
        offset += pageSize
        scope.launch { itemAtEndLoad(offset) }
    }
}