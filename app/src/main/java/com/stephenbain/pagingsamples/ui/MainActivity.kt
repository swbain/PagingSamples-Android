package com.stephenbain.pagingsamples.ui

import android.os.Bundle
import androidx.navigation.findNavController
import com.stephenbain.pagingsamples.R


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}