package com.stephenbain.pagingsamples.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stephenbain.pagingsamples.data.model.Playlist


@Database(entities = [Playlist::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
}