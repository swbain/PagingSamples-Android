package com.stephenbain.pagingsamples.ui.login

import android.content.Intent
import android.os.Bundle
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.stephenbain.pagingsamples.R
import com.stephenbain.pagingsamples.ui.BaseActivity
import com.stephenbain.pagingsamples.ui.MainActivity
import com.stephenbain.pagingsamples.ui.observeEvent
import com.stephenbain.pagingsamples.ui.snackbar
import com.stephenbain.pagingsamples.util.CLIENT_ID
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private val viewModel by lazy { getViewModel<LoginViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.showHome.observeEvent(this) { launchHome() }
        viewModel.showLoginPrompt.observeEvent(this) { launchLoginPrompt() }
        viewModel.showError.observeEvent(this) { showError(it) }

        logIn.setOnClickListener { viewModel.userClicksLogin() }
    }

    private fun launchLoginPrompt() {
        val builder = AuthenticationRequest.Builder(
            CLIENT_ID,
            AuthenticationResponse.Type.TOKEN,
            REDIRECT_URI
        )
        builder.setScopes(arrayOf("playlist-read-private"))
        builder.setShowDialog(false)
        val request = builder.build()
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    private fun launchHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showError(error: String) = snackbar(view = logIn, text = error)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, data)
            when {
                response.type == AuthenticationResponse.Type.TOKEN -> viewModel.userReceivesToken(response.accessToken)
                response.type == AuthenticationResponse.Type.ERROR -> viewModel.userReceivesError(response.error)
                else -> viewModel.userReceivesError("Unknown error")
            }
        }
    }

    companion object {
        private const val REDIRECT_URI = "sample://callback"
        private const val REQUEST_CODE = 1337
    }
}