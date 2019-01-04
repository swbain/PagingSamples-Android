package com.stephenbain.pagingsamples.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stephenbain.pagingsamples.R
import com.stephenbain.pagingsamples.data.model.AlbumSimple
import com.stephenbain.pagingsamples.data.model.Playlist
import com.stephenbain.pagingsamples.ui.BaseFragment
import com.stephenbain.pagingsamples.ui.observeNotNull
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_new_releases.*
import kotlinx.android.synthetic.main.item_album.view.*


class MyPlaylistsFragment : BaseFragment() {

    private val viewModel by lazy { getViewModel<MyPlaylistsViewModel>() }
    private val adapter by lazy { PlaylistAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_releases, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(activity)

        viewModel.playlists.observeNotNull(this) { showPlaylists(it) }

    }

    private fun showPlaylists(playlists: PagedList<Playlist>) {
        adapter.submitList(playlists)
    }
}

private class PlaylistAdapter : PagedListAdapter<Playlist, PlaylistViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return PlaylistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Playlist>() {
            override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
                return oldItem == newItem
            }

        }
    }
}

private class PlaylistViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(playlist: Playlist?) {
        containerView.title.text = playlist?.name
        if (playlist == null) {
            containerView.setBackgroundColor(containerView.resources.getColor(R.color.material_blue_grey_900))
        } else {
            containerView.setBackgroundColor(containerView.resources.getColor(R.color.background_material_light))
        }
    }

}