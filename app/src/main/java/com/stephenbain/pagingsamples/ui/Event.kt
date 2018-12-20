package com.stephenbain.pagingsamples.ui


/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * Stolen from https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(listener: (T) -> Unit) {
        if (!hasBeenHandled) {
            hasBeenHandled = true
            listener(content)
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}