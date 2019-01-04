package com.stephenbain.pagingsamples.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


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

@Entity
data class Playlist(val id: String, val name: String, @PrimaryKey(autoGenerate = true) val key: Int)