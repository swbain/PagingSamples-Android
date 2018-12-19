package com.stephenbain.pagingsamples.data.model


data class AlbumSimple(
    val uri: String,
    val id: String,
    val release_date: String,
    val release_date_precision: String,
    val name: String
)

data class Paging<T>(
    val href: String,
    val items: List<T>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)

data class AlbumsResponse(val albums: Paging<AlbumSimple>)