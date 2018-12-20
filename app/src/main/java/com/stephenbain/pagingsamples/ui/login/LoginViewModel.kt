package com.stephenbain.pagingsamples.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stephenbain.pagingsamples.data.repo.TokenRepository
import com.stephenbain.pagingsamples.ui.Event
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val tokenRepository: TokenRepository) : ViewModel() {

    private val _showHome = MutableLiveData<Event<Unit>>()
    val showHome: LiveData<Event<Unit>>
        get() = _showHome

    private val _showError = MutableLiveData<Event<String>>()
    val showError: LiveData<Event<String>>
        get() = _showError

    private val _showLoginPrompt = MutableLiveData<Event<Unit>>()
    val showLoginPrompt: LiveData<Event<Unit>>
        get() = _showLoginPrompt

    fun userClicksLogin() {
        _showLoginPrompt.postValue(Event(Unit))
    }

    fun userReceivesToken(token: String) {
        tokenRepository.token = token
        _showHome.postValue(Event(Unit))
    }

    fun userReceivesError(error: String) {
        _showError.postValue(Event(error))
    }

}