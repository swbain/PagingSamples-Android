package com.stephenbain.pagingsamples.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.stephenbain.pagingsamples.R
import com.stephenbain.pagingsamples.ui.BaseFragment
import com.stephenbain.pagingsamples.ui.observeEvent
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {

    private val viewModel by lazy { getViewModel<HomeViewModel>() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.launchNewReleases.observeEvent(this) { launchNewReleases() }
        newReleases.setOnClickListener { viewModel.userClicksNewRelases() }
    }

    private fun launchNewReleases() = findNavController().navigate(R.id.action_homeFragment_to_newReleasesFragment)

}