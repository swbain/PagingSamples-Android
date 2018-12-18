package com.stephenbain.pagingsamples.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stephenbain.pagingsamples.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val token = intent.getStringExtra(KEY_TOKEN)
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val KEY_TOKEN = "token"

        fun newIntent(context: Context, token: String) = Intent(context, MainActivity::class.java).apply {
            putExtra(KEY_TOKEN, token)
        }
    }

}