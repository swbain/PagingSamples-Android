package com.stephenbain.pagingsamples.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stephenbain.pagingsamples.ui.Event
import javax.inject.Inject


class HomeViewModel @Inject constructor() : ViewModel() {

    private val _launchNewReleases = MutableLiveData<Event<Unit>>()
    val launchNewReleases: LiveData<Event<Unit>>
        get() = _launchNewReleases

    fun userClicksNewRelases() = _launchNewReleases.postValue(Event(Unit))

}