package com.stephenbain.pagingsamples.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stephenbain.pagingsamples.data.datasource.NewReleasesDataSourceFactory
import com.stephenbain.pagingsamples.data.domain.GetNewReleases
import com.stephenbain.pagingsamples.data.model.AlbumSimple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject


class MainViewModel @Inject constructor(private val getNewReleases: GetNewReleases) : ViewModel() {

    private val parentJob = Job()
    private val scope = CoroutineScope(Dispatchers.Main + parentJob)

    private val token = MutableLiveData<String>()

    val newReleases: LiveData<PagedList<AlbumSimple>> = switchMap(token) {
        val config = PagedList.Config.Builder().apply {
            setPageSize(10)
            setPrefetchDistance(30)
            setEnablePlaceholders(true)
        }.build()

        val factory = NewReleasesDataSourceFactory(scope, getNewReleases, it)
         LivePagedListBuilder(factory, config).build()
    }

    fun setToken(tokenText: String) {
        token.postValue(tokenText)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}