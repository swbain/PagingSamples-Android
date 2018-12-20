package com.stephenbain.pagingsamples.data.repo

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenRepository @Inject constructor() {
    var token = ""
}