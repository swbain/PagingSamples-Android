package com.stephenbain.pagingsamples.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stephenbain.pagingsamples.data.model.Playlist


@Dao
interface PlaylistDao {

    @Query("SELECT * FROM playlist")
    fun getAllPlaylists(): DataSource.Factory<Int, Playlist>

    @Insert
    suspend fun insertAll(playlists: List<Playlist>)

    @Query("SELECT COUNT(*) FROM playlist")
    suspend fun getItemCount(): Int

}