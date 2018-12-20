package com.stephenbain.pagingsamples.ui.home

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
import com.stephenbain.pagingsamples.ui.BaseActivity
import com.stephenbain.pagingsamples.ui.observeNotNull
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_album.view.*


class HomeActivity : BaseActivity() {

    private val viewModel by lazy { getViewModel<HomeViewModel>() }

    private val adapter by lazy { NewReleasesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        viewModel.newReleases.observeNotNull(this, ::showList)
    }

    private fun showList(items: PagedList<AlbumSimple>) {
        adapter.submitList(items)
    }
}

private class NewReleasesAdapter : PagedListAdapter<AlbumSimple, NewReleasesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewReleasesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return NewReleasesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewReleasesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlbumSimple>() {
            override fun areItemsTheSame(oldItem: AlbumSimple, newItem: AlbumSimple): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AlbumSimple, newItem: AlbumSimple): Boolean {
                return oldItem == newItem
            }

        }
    }
}

private class NewReleasesViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(album: AlbumSimple?) {
        containerView.title.text = album?.name
        if (album == null) {
            containerView.setBackgroundColor(containerView.resources.getColor(R.color.material_blue_grey_900))
        } else {
            containerView.setBackgroundColor(containerView.resources.getColor(R.color.background_material_light))
        }
    }
}
