package com.stephenbain.pagingsamples.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stephenbain.pagingsamples.R
import com.stephenbain.pagingsamples.data.model.AlbumSimple
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_album.view.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelProvider: ViewModelProvider.Factory

    private val adapter by lazy { NewReleasesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        val token = intent.getStringExtra(KEY_TOKEN)

        val viewModel = viewModelProvider.create(MainViewModel::class.java)
        viewModel.setToken(token)
        viewModel.newReleases.observeNotNull(this, ::showList)
    }

    private fun showList(items: PagedList<AlbumSimple>) {
        adapter.submitList(items)
    }

    companion object {
        private const val KEY_TOKEN = "token"

        fun newIntent(context: Context, token: String) = Intent(context, MainActivity::class.java).apply {
            putExtra(KEY_TOKEN, token)
        }
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
