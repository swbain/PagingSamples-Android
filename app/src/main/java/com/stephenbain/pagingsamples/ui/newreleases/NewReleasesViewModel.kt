package com.stephenbain.pagingsamples.ui.newreleases

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.stephenbain.pagingsamples.data.datasource.NewReleasesDataSourceFactory
import com.stephenbain.pagingsamples.data.domain.GetNewReleases
import com.stephenbain.pagingsamples.data.model.AlbumSimple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject


class NewReleasesViewModel @Inject constructor(private val getNewReleases: GetNewReleases) : ViewModel() {

    private val parentJob = Job()
    private val scope = CoroutineScope(Dispatchers.Main + parentJob)

    val newReleases: LiveData<PagedList<AlbumSimple>>
        get() {
            val config = PagedList.Config.Builder().apply {
                setPageSize(10)
                setPrefetchDistance(20)
                setEnablePlaceholders(true)
            }.build()

            val factory = NewReleasesDataSourceFactory(scope, getNewReleases)
            return factory.toLiveData(config)
        }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}